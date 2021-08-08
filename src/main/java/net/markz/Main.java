package net.markz;

import net.markz.services.event.EventListener;
import net.markz.services.event.EventServiceImpl;
import net.markz.services.event.TimeElapsedEventListener;
import net.markz.services.fileread.FileReadingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.util.HashSet;

public class Main {
  private static final Logger LOGGER = LogManager.getLogger(Main.class);

  public static void main(String[] args) {

    LOGGER.info("Application started running...");

    var eventListeners = new HashSet<EventListener>();
    eventListeners.add(new TimeElapsedEventListener());
    var resource =
        new Resource(new EventServiceImpl(), new FileReadingServiceImpl(), eventListeners);

    // Force JIT optimizations to make the performance measurements for calculating the ETAs faster
    // and more accurate. Obviously in production environment this program will probably be
    // constantly running and there will be no need to warm up JIT.
    resource.nukeTheJIT();
    // Run the calculations with optimal speed.
    resource.calculateAllETAs(Paths.get("./src/test/scripts/smallNumbers.txt"));
  }
}
