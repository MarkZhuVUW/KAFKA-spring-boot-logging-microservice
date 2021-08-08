package net.markz.services.fileread;

/**
 * An Algorithm functional interface that inverts the control of how the memory exhaustion is
 * calculated.
 */
public interface Algorithm {
  /**
   * Do the actual algorithm, taking in a configuration object.
   *
   * @param configuration a configuration object.
   * @return
   */
  long doAlgorithm(Configuration configuration);
}
