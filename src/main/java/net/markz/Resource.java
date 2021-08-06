package net.markz;

import net.markz.services.event.EventService;
import net.markz.services.event.EventServiceImpl;
import net.markz.services.event.EventType;
import net.markz.services.event.TimeElapsedEventListener;
import net.markz.services.fileread.FileReadingService;
import net.markz.services.fileread.FileReadingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * A unit-testable "top" layer class of the application. All exceptions should be handled here
 * following the "throw early, catch late" practice.
 */
public class Resource {
  private static final Logger LOGGER = LogManager.getLogger(Resource.class);
  private static final String SEPARATOR =
      "--------------------------------------------------------------------------------------"
          + "--------------------------------------------------------------";

  EventService eventService;
  FileReadingService fileReadingService;
  /**
   * Dependency inject the services for unit-testability.
   *
   * @param eventService event service.
   * @param fileReadingService file reading service.
   */
  public Resource(EventService eventService, FileReadingService fileReadingService) {
    this.eventService = eventService;
    this.fileReadingService = fileReadingService;
  }

  /**
   * A simple method that forces JIT to: 1. Compile all classes needed to run the program into
   * machine instructions before they are used rather than compiling them "JUST-IN-TIME" 2. Force
   * the "hottest" Hotspot optimization on all relevant stack and heap memory.
   */
  public void nukeTheJIT() {
    LOGGER.debug(SEPARATOR);
    LOGGER.debug(SEPARATOR);
    LOGGER.debug(SEPARATOR);
    for (var i = 0; i < 1; i++) {
      var es = new EventServiceImpl();
      var timeElapsedEventListener = new TimeElapsedEventListener();
      es.addEventListener(timeElapsedEventListener);
      var fs = new FileReadingServiceImpl();
      var eventBuilder = es.startEvent(EventType.FILE_READ_TIME_ELAPSED);
      var config = fs.readLineIntoConfig("1 1");
      fs.calculateETA(config);
      es.endEvent(eventBuilder);
    }
    LOGGER.debug(
        "Now the JIT compiler has compiled all classed needed so that the subsequent elapsed times will be more accurate!");
    LOGGER.debug(SEPARATOR);
    LOGGER.debug(SEPARATOR);
    LOGGER.debug(SEPARATOR);
  }

  /**
   * Calculates ETA before OOM for each line read from "numbers.txt" with a good level of exception
   * handling, general logging and time elapsed logging.
   */
  public void calculateAllETAs() {

    LOGGER.info("Application started running...");

    var timeElapsedEventListener = new TimeElapsedEventListener();
    this.eventService.addEventListener(timeElapsedEventListener);
    try (Stream<String> stream = Files.lines(Paths.get("./src/test/scripts/smallNumbers.txt"))) {
      stream.forEach(
          lineStr -> {
            var eventBuilder = eventService.startEvent(EventType.FILE_READ_TIME_ELAPSED);
            var config = fileReadingService.readLineIntoConfig(lineStr);
            var eta = fileReadingService.calculateETA(config);
            eventService.endEvent(eventBuilder);
            LOGGER.info("Remaining time before OOP: {{}} seconds", eta);
          });
      // IOException normally should not stop program as it gives user horrible times.
      // IllegalArgumentException is due to a listener being not properly attached or removed and it
      // is not serious enough to cause a program exit.
      // All other exceptions should be logged and force a program exit.
    } catch (IOException | IllegalArgumentException e) {
      LOGGER.warn("Stack trace: {{}} Application stopped with {{}}", e, e.getCause().toString());
    } catch (Exception e) {
      LOGGER.error("Stack trace: {{}} Application stopped with {{}}", e, e.getCause().toString());
      throw e;
    } finally {
      // In larger applications, the service objects are used everywhere and if we do not cleanup
      // the side effects in time, the objects(in this case the timeElapsedEventListener object)
      // will never be garbage-collected and eventually an OutOfMemoryException will occur if such
      // objects overflow the heap!
      eventService.removeEventListener(timeElapsedEventListener);
    }
  }
}
