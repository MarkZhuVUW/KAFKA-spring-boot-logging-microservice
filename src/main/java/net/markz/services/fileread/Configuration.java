package net.markz.services.fileread;

import java.util.List;
import java.util.Set;

public class Configuration {

  /**
   * A long representing the available disk space in bytes. Design consideration: I have noticed
   * that in the 'numbers.txt' file the 'X' could be pretty big. This drives my decision to use long
   * to represent available disk space in bytes because in java it can hold data as large as the
   * computer RAM. If even that is not enough, my idea is that we can do some preprocessing on the
   * text file so that instead of representing available disk space in bytes, we represent it in
   * Megabytes or even Gigabytes.
   */
  private final long availableDiskSpace;
  /**
   * A List of bytes representing d(p_1) d(p_2) ... d(p_n) in the test question. Some design
   * consideration: 1. I chose to use a List of bytes to represent time in seconds for processes to
   * consume 1 byte of disk space because byte is the cheapest integer data type in Java (taking up
   * ) and I assume a process can consume one byte pretty fast... 2. Although a list of Byte objects
   * consume more stack and heap memory than, for example, an array of primitive bytes, the java
   * parallelStream api can be used on a List so that we can concurrently calculate the ETAs.
   */
  private final List<Integer> diskConsumptionSpeeds;

  private Configuration(Builder builder) {
    this.availableDiskSpace = builder.availableDiskSpace;
    this.diskConsumptionSpeeds = builder.diskConsumptionSpeeds;
  }

  public long getAvailableDiskSpace() {
    return availableDiskSpace;
  }

  public List<Integer> getDiskConsumptionSpeeds() {
    return diskConsumptionSpeeds;
  }

  public static class Builder {
    private long availableDiskSpace;
    private List<Integer> diskConsumptionSpeeds;

    public Builder withAvailableDiskSpace(long availableDiskSpace) {
      this.availableDiskSpace = availableDiskSpace;
      return this;
    }

    public Builder withDiskConsumptionSpeeds(List<Integer> diskConsumptionSpeeds) {
      if (diskConsumptionSpeeds == null) {
        throw new IllegalArgumentException();
      }
      this.diskConsumptionSpeeds = diskConsumptionSpeeds;
      return this;
    }

    public Configuration build() {
      return new Configuration(this);
    }
  }
}
