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

  private static void triggerJITCompilations() {
    var separator =
        "----------------------------------------------------------------------------------------------------------------------------------------------------";
    LOGGER.error(separator);
    LOGGER.error(separator);
    LOGGER.error(separator);
    LOGGER.error(
        "This is to trigger JIT compiler optimizations before we read and measure performance.");
    EventService eventService = new EventServiceImpl();
    var eventListener = new LogEventListener();
    eventService.addEventListener(eventListener);
    FileReadingService fileReadingService = new FileReadingServiceImpl();
    var eb = eventService.startEvent(EventType.FILE_READ_LOG);
    var c = fileReadingService.readLineIntoConfig("123 123 123");
    fileReadingService.calculateETA(c);
    eventService.endEvent(eb);
    LOGGER.error(
        "The JIT compiler has compiled all classed needed so the subsequent time elapsed will be more accurate!");

    LOGGER.error(separator);
    LOGGER.error(separator);
    LOGGER.error(separator);
  }

  public static void main(String[] args) {
    triggerJITCompilations();

    LOGGER.error("Application started running...");
    EventService eventService = new EventServiceImpl();
    var eventListener = new LogEventListener();
    eventService.addEventListener(eventListener);
    FileReadingService fileReadingService = new FileReadingServiceImpl();
    try (Stream<String> stream = Files.lines(Paths.get("./src/test/scripts/smallNumbers.txt"))) {

      stream.forEach(
          lineStr -> {
            var eventBuilder = eventService.startEvent(EventType.FILE_READ_LOG);
            var config = fileReadingService.readLineIntoConfig(lineStr);
            var eta = fileReadingService.calculateETA(config);
            eventService.endEvent(eventBuilder);
            LOGGER.error("Remaining time before melt down: {{}} seconds", eta);
          });
    } catch (IOException e) {
      LOGGER.error(e);
    } finally {
      LOGGER.error("Application finished running");
    }
  }
}
