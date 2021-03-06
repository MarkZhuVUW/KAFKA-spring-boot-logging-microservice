package net.markz.services.fileread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * The FileReadingServiceImpl is the only implementation of the {@link FileReadingService}.
 * Singleton pattern is used here as logically there should only be one EventServiceImpl object in
 * the program.
 */
public final class FileReadingServiceImpl implements FileReadingService {
  private static final Logger LOGGER = LogManager.getLogger(FileReadingServiceImpl.class);
  private static FileReadingServiceImpl singleton;

  /** Encapsulate constructor here for the singleton pattern. */
  private FileReadingServiceImpl() {}

  /**
   * Singleton pattern. Lazy initialize a {@link FileReadingServiceImpl} object here.
   *
   * @return the singleton {@link FileReadingServiceImpl} object.
   */
  public static FileReadingServiceImpl getInstance() {
    if (singleton == null) {
      singleton = new FileReadingServiceImpl();
    }
    return singleton;
  }

  /**
   * Reads a line string into a configuration object.
   *
   * @param lineStr line string.
   * @return a configuration object.
   */
  @Override
  public Configuration readLineIntoConfig(String lineStr) {
    LOGGER.info("Started parsing line into a configuration object.");
    var splitLine = lineStr.split(" ");
    var availableDiskSpace = Long.parseLong(splitLine[0]);
    var consumptionSpeeds = Arrays.stream(splitLine).skip(1).map(Integer::parseInt).toList();
    Configuration config =
        new Configuration.Builder()
            .withAvailableDiskSpace(availableDiskSpace)
            .withDiskConsumptionSpeeds(consumptionSpeeds)
            .build();
    LOGGER.info("Finished parsing line into a configuration object.");
    return config;
  }

  /**
   * Calculates the ETA before out of memory for the input configuration. The control of the
   * calculation algorithm is inverted.
   *
   * @param algo A functional interface that defines how the calculation is done.
   * @param configuration configuration object.
   * @return
   */
  @Override
  public long calculateETA(Algorithm algo, Configuration configuration) {
    LOGGER.info(
        "Started calculating ETAs for configuration with available disk space: {{}} bytes and {{}} processes.",
        configuration.getAvailableDiskSpace(),
        configuration.getDiskConsumptionSpeeds().size());

    long eta = algo.doAlgorithm(configuration);
    LOGGER.info(
        "Finished calculating ETAs for configuration with available disk space: {{}} bytes and {{}} processes. Resuting ETA: {{}} seconds",
        configuration.getAvailableDiskSpace(),
        configuration.getDiskConsumptionSpeeds().size(),
        eta);
    return eta;
  }

  /** Calculate the total disk consumption for the slowest thread. */
  private long calculateDiskConsumptionForSlowestThread(
      Integer[] sortedConsumptionSpeeds, int gcd) {
    long diskSpaceConsumed = 0;
    int eta = sortedConsumptionSpeeds[0];
    int length = sortedConsumptionSpeeds.length;
    while (eta != sortedConsumptionSpeeds[length - 1]) {
      for (int speed : sortedConsumptionSpeeds) {
        if (eta % speed == 0) {
          diskSpaceConsumed++;
        }
      }
      eta += gcd;
    }
    return 0;
  }
}
