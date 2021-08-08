package net.markz;

import net.markz.services.event.*;
import net.markz.services.fileread.FileReadingService;
import net.markz.services.fileread.FileReadingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
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

  private final EventService eventService;
  private final FileReadingService fileReadingService;
  private final Set<EventListener> listeners;

  /**
   * Dependency inject the services for unit-testability.
   *
   * @param eventService event service.
   * @param fileReadingService file reading service.
   */
  public Resource(
      EventService eventService,
      FileReadingService fileReadingService,
      Set<EventListener> eventListeners) {
    this.eventService = eventService;
    this.listeners = eventListeners;
    this.fileReadingService = fileReadingService;
  }

  /**
   * A simple method that forces JIT to: 1. Compile all classes needed to run the program into
   * machine instructions before they are used rather than compiling them "JUST-IN-TIME" 2. the
   * "hottest" Hotspot optimization on all relevant stack and heap memory.
   */
  public void nukeTheJIT() {
    LOGGER.debug(SEPARATOR);
    LOGGER.debug(SEPARATOR);
    LOGGER.debug(SEPARATOR);
    LOGGER.debug("Start JIT optimizations.....");
    for (var i = 0; i < 10000; i++) {
      calculateAllETAs(Paths.get("./src/test/scripts/optimizeJIT.txt"));
    }
    // Now the JIT compiler is nuclear-hot and it has compiled all classed needed o that the
    // subsequent elapsed times will be faster and more accurate!
    // I am not doing anything here in order to keep JIT warm when we are doing performance
    // logging.

  }

  /**
   * Calculates ETA before OOM for each line read from "numbers.txt" with a good level of exception
   * handling, general logging and time elapsed logging.
   */
  public void calculateAllETAs(Path path) {

    try (Stream<String> stream = Files.lines(path)) {
      listeners.forEach(eventService::addEventListener);
      stream.forEach(
          lineStr -> {
            var eventBuilder = eventService.startEvent(EventType.FILE_READ_TIME_ELAPSED);
            var config = fileReadingService.readLineIntoConfig(lineStr);
            var eta = fileReadingService.calculateETA(AlgorithmResource.bruteforce, config);
            eventBuilder.withValue(eta);
            eventService.endEvent(eventBuilder);
          });
      // IOException normally should not stop program as it gives user horrible times.
      // IllegalArgumentException is due to a listener being not properly attached or removed and it
      // is not serious enough to cause a program exit.
      // All other exceptions should be logged and force a program exit.
    } catch (IOException | IllegalArgumentException e) {
      LOGGER.warn("Exception happened: {{}}", e.toString());
      e.printStackTrace();
    } catch (Exception e) {
      LOGGER.error("Application stopped with: {{}}", e.toString());
      throw e;
    } finally {
      // In larger applications, the service objects are used everywhere and if we do not cleanup
      // the side effects in time, the objects(in this case the timeElapsedEventListener object)
      // will never be garbage-collected and eventually an out of memory Exception will occur if
      // such objects overflow the heap!
      listeners.forEach(eventService::removeEventListener);
    }
  }
}
