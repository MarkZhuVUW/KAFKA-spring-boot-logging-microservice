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
        new ResourceProvider(
            EventServiceImpl.getInstance(), FileReadingServiceImpl.getInstance(), eventListeners);

    // For demonstration purpose I am forcing JIT optimizations to make the performance measurements
    // calculating the ETAs faster and more accurate. Obviously in production environment this
    // program will probably be constantly running and there will be no need to warm up JIT
    // beforehand.
    resource.nukeTheJIT(
        Paths.get("./src/test/scripts/smallNumbers.txt"), Algorithms.BRUTE_FORCE);
    // Run the actual calculations with optimal speed. The brute force approach works only for small
    // configurations...
    resource.calculateAllETAs(
        Paths.get("./src/test/scripts/smallNumbers.txt"), Algorithms.BRUTE_FORCE);

    // This is not working but I have included here for reference.
    //    resource.nukeTheJIT(
    //        Paths.get("./src/test/scripts/numbers.txt"), AlgorithmResource.SMARTER_OR_NOT_ALGO);
    //    resource.calculateAllETAs(
    //        Paths.get("./src/test/scripts/numbers.txt"), AlgorithmResource.SMARTER_OR_NOT_ALGO);
  }
}
