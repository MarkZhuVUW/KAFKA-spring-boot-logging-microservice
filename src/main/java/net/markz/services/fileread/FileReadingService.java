package net.markz.services.fileread;

/** The FileReadingService defines a set of operations we can do to a line of file. */
public interface FileReadingService {
  /**
   * Reads a line string into a configuration object.
   *
   * @param lineStr line string.
   * @return a configuration object.
   */
  Configuration readLineIntoConfig(String lineStr);

  /**
   * Calculates the ETA before out of memory using the parsed configuration object.
   *
   * @param configuration configuration object.
   * @return ETA in seconds.
   */
  long calculateETA(Algorithm algo, Configuration configuration);
}
