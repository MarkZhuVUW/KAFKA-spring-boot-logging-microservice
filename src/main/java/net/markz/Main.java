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
    try (Stream<String> stream = Files.lines(Paths.get("./src/test/scripts/numbers.txt"))) {
      stream.forEach(
          lineStr -> {
            var eventBuilder = eventService.startEvent(EventType.FILE_READ_LOG);
            eventService.endEvent(eventBuilder);
            var config = fileReadingService.readLineIntoConfig(lineStr);
            fileReadingService.calculateETA(config);
          });

    } catch (IOException e) {
      LOGGER.error(e);
    } finally {
      eventService.removeEventListener(eventListener);
      LOGGER.error("Application finished running");
    }
  }
}
