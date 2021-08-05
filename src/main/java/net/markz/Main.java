package net.markz;

import net.markz.services.event.*;
import net.markz.services.fileread.FileReadingService;
import net.markz.services.fileread.FileReadingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
  private static final Logger LOGGER = LogManager.getLogger(Main.class);

  public static void main(String[] args) {
    LOGGER.error("Application started running...");
    EventService eventService = new EventServiceImpl();
    var eventListener = new LogEventListener();
    eventService.addEventListener(eventListener);
    FileReadingService fileReadingService = new FileReadingServiceImpl();
    try (Stream<String> stream = Files.lines(Paths.get("./src/test/scripts/smallNumbers.txt"))) {
      var totalNumOfSeconds =
          stream
              .map(
                  lineStr -> {
                    var eventBuilder = eventService.startEvent(EventType.FILE_READ_LOG);
                    var config = fileReadingService.readLineIntoConfig(lineStr);
                    var eta = fileReadingService.calculateETA(config);
                    eventService.endEvent(eventBuilder);
                    LOGGER.error("ETA calculation finished taking: {{}} seconds", eta);

                    return eta;
                  })
              .reduce(Long::sum);
      LOGGER.error(
          "Total number of seconds taken to run through the entire file: {{}} ", totalNumOfSeconds);
    } catch (IOException e) {
      LOGGER.error(e);
    } finally {
      LOGGER.error("Application finished running");
    }
  }
}
