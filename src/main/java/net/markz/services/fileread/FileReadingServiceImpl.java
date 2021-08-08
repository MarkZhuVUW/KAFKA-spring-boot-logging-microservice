package net.markz.services.fileread;

import net.markz.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class FileReadingServiceImpl implements FileReadingService {
  private static final Logger LOGGER = LogManager.getLogger(FileReadingServiceImpl.class);

  @Override
  public Configuration readLineIntoConfig(String lineStr) {
    var splitLine = lineStr.split(" ");

    var availableDiskSpace = Long.parseLong(splitLine[0]);
    var sortedConsumptionSpeeds = Arrays.stream(splitLine).skip(1).map(Integer::parseInt).toList();

    return new Configuration.Builder()
        .withAvailableDiskSpace(availableDiskSpace)
        .withDiskConsumptionSpeeds(sortedConsumptionSpeeds)
        .build();
  }

  @Override
  public long calculateETA(Algorithm algo, Configuration configuration) {
    return algo.doAlgorithm(configuration);
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
