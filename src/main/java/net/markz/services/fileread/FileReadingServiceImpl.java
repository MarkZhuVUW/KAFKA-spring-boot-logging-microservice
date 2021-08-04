package net.markz.services.fileread;

import java.math.BigInteger;
import java.util.Arrays;

public class FileReadingServiceImpl implements FileReadingService {

  @Override
  public Configuration readLineIntoConfig(String lineStr) {
    var splitLine = lineStr.split(" ");
    var availableDiskSpace = Long.parseLong(splitLine[0]);
    var diskConsumptionSpeeds = Arrays.stream(splitLine).skip(1).map(Integer::parseInt).toList();

    return new Configuration.Builder()
        .withAvailableDiskSpace(availableDiskSpace)
        .withDiskConsumptionSpeeds(diskConsumptionSpeeds)
        .build();
  }

  @Override
  public long calculateETA(Configuration configuration) {
    var diskConsumptionSpeeds = configuration.getDiskConsumptionSpeeds();
    var availableDiskSpace = configuration.getAvailableDiskSpace();
    long eta = 1;

    long consumedDiskSpace = 0;
    // If the number of processes is greater than 1000, we use parallelStream to concurrently
    // Calculate ETAs, otherwise run it in single thread.
    //    if (diskConsumptionSpeeds.size() > 1000) {
    while (consumedDiskSpace <= availableDiskSpace) {
      for (long speed : diskConsumptionSpeeds) {
        if (eta % speed == 0) {
          consumedDiskSpace++;
        }
        if (consumedDiskSpace == availableDiskSpace) return eta;
      }
      eta++;
    }
    //    }
    return eta;
  }
}
