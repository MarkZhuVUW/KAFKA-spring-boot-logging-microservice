package net.markz.services.fileread;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/** A configuration, extracted from the test question. */
public class Configuration {

  /**
   * A long representing the available disk space in bytes. Design consideration: According to an
   * old website: https://www.awsthinkbox.com/blogs/the-evolution-of-rendering. The render farm has
   * three petabytes of local storage. Assuming now that there is 9000 petabytes local storage. 9000
   * petabytes = 9e+18 in decimal, whereas a long in java is [-9.223372e+18, 9.223372e+18]. Even the
   * positive part of a long can represent 9000 petabytes.
   */
  private final long availableDiskSpace;

  /**
   * A list of integers representing one byte of memory consumption time in seconds for the
   * processes. Design consideration: According to the internet and the numbers.txt file, we are
   * looking at roughly 400000 threads in the render farm. Let us make it larger to 1 million
   * thread. A list of 1 million Integer objects can take up(depending on JVM implementation): 4
   * megabytes or 8 megabytes memory for object reference + 4 megabytes primitive ints the objects
   * wrap. The stack and heap memories should be well enough for us to handle this amount of data.
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Configuration that = (Configuration) o;
    return getAvailableDiskSpace() == that.getAvailableDiskSpace() && getDiskConsumptionSpeeds().equals(that.getDiskConsumptionSpeeds());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getAvailableDiskSpace(), getDiskConsumptionSpeeds());
  }

  /**
   * I give the Configuration class a builder here in case more fields are added to it in the
   * future.
   */
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
