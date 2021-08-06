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
  private static final String SEPARATOR =
      "--------------------------------------------------------------------------------------"
          + "--------------------------------------------------------------";

  /**
   * This is a simple method that forces JIT to compile all classes needed to run the program before
   * they are used rather than compiling them "JUST-IN-TIME" so that we can get more accurate
   * performance measurements of the ETA calculations. We can see how the first ETA calculation is
   * so much slower than subsequent ones by looking at the log4j2 logs.
   */
  private static void fireUpJITCompilations() {
    LOGGER.error(SEPARATOR);
    LOGGER.error(SEPARATOR);
    LOGGER.error(SEPARATOR);
    EventService eventService = new EventServiceImpl();
    var timeElapsedEventListener = new TimeElapsedEventListenerImpl();
    eventService.addEventListener(timeElapsedEventListener);
    FileReadingService fileReadingService = new FileReadingServiceImpl();
    var eventBuilder = eventService.startEvent(EventType.FILE_READ_TIME_ELAPSED);
    var config = fileReadingService.readLineIntoConfig("1 1");
    fileReadingService.calculateETA(config);
    eventService.endEvent(eventBuilder);
    LOGGER.error(
        "Now the JIT compiler has compiled all classed needed so that the subsequent elapsed times will be more accurate!");
    LOGGER.error(SEPARATOR);
    LOGGER.error(SEPARATOR);
    LOGGER.error(SEPARATOR);
  }

  public static void main(String[] args) {
    // Force the poor Hotspot JIT against its will to compile all classes beforehand...
    fireUpJITCompilations();

    LOGGER.error("Application started running...");
    EventService eventService = new EventServiceImpl();
    var timeElapsedEventListener = new TimeElapsedEventListenerImpl();
    eventService.addEventListener(timeElapsedEventListener);
    FileReadingService fileReadingService = new FileReadingServiceImpl();
    try (Stream<String> stream = Files.lines(Paths.get("./src/test/scripts/smallNumbers.txt"))) {
      stream.forEach(
          lineStr -> {
            var eventBuilder = eventService.startEvent(EventType.FILE_READ_TIME_ELAPSED);
            var config = fileReadingService.readLineIntoConfig(lineStr);
            var eta = fileReadingService.calculateETA(config);
            eventService.endEvent(eventBuilder);
            LOGGER.error("Remaining time before melt down: {{}} seconds", eta);
          });
    } catch (IOException e) {
      LOGGER.error("Application stopped with IOException: {{}}", e.toString());

    } finally {
      LOGGER.error("Application finished running");
    }
  }
}
