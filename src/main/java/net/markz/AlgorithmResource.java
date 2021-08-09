package net.markz;

import net.markz.services.fileread.Algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/** Keeps track of a few ways to calculate ETA for out of memory of a configuration. */
public class AlgorithmResource {
  // hide constructor as we are only using the static functions from outside.
  private AlgorithmResource() {}

  /**
   * I call it the brute-force way of calculating ETAs. This essentially keeps incrementing the eta
   * in seconds by the "greatest common divider" of all thread consumption speeds starting from 0
   * and checks for all disk consumption speeds whether any of them can be divided by the current
   * eta. If they can we decrement availableDiskSpace and if availableDiskSpace equals 0 we get the
   * ETA in seconds... This becomes extremely slow even at just the fourth line of the
   * numbers.txt....but I it generates the correct output given enough time..
   */
  public static final Algorithm BRUTE_FORCE =
      configuration -> {
        // Sort the thread consumption speeds fast to slow.
        var sortedConsumptionSpeeds =
            configuration.getDiskConsumptionSpeeds().stream().sorted().toList();
        long availableDiskSpace = configuration.getAvailableDiskSpace();
        // I know for all lines in numbers.txt the gcd is always 1 but in case our data can use
        // something like 59182 as the gcd, this can actually greatly speed up this algorithm...
        int gcd = findGCD(sortedConsumptionSpeeds);
        // Start from the fastest thread consumption speed to save a little bit of time...
        long eta = sortedConsumptionSpeeds.get(0);
        while (true) {
          for (int speed : sortedConsumptionSpeeds) {
            if (eta % speed == 0) {
              availableDiskSpace--;
              if (availableDiskSpace == 0) return eta;
            }
          }
          eta += gcd;
        }
      };

  /** */
  public static final Algorithm SMARTER_OR_NOT_ALGO =
      configuration -> {
        var sortedConsumptionSpeeds =
            configuration.getDiskConsumptionSpeeds().stream().sorted().toList();
        long availableDiskSpace = configuration.getAvailableDiskSpace();
        int length = sortedConsumptionSpeeds.size();

        int[] dynamicConsumptionSpeeds = new int[length];
        for (var i = 0; i < length; i++) {
          dynamicConsumptionSpeeds[i] = sortedConsumptionSpeeds.get(i);
        }

        //        int[] consumptionSpeedDiffs = new int[length];
        //        for (var i = 0; i <length; i++) {
        //
        //        }
        long eta = 0;
        long round = 1;
        while (true) {
          for (var i = 0; i < length; i++) {
            int consumptionSpeedDiffs =
                i == 0
                    ? dynamicConsumptionSpeeds[i]
                    : dynamicConsumptionSpeeds[i] - dynamicConsumptionSpeeds[i - 1];
            for (var j = 0; j < i; j++) {
              while (consumptionSpeedDiffs > 0) {
                consumptionSpeedDiffs--;
                dynamicConsumptionSpeeds[j]--;
                eta++;
                if (dynamicConsumptionSpeeds[j] == 0) {
                  dynamicConsumptionSpeeds[j] = sortedConsumptionSpeeds.get(j);
                  availableDiskSpace--;
                  if (availableDiskSpace == 0) return eta;
                }
              }
              //              eta -= consumptionSpeedDiffs;
            }
            eta += consumptionSpeedDiffs;
            availableDiskSpace--;
            round++;
            if (availableDiskSpace == 0) return eta;
          }
        }
      };

  /**
   * Get gcd of a and b.
   *
   * @param a integer a
   * @param b integer b
   * @return gcd of a and b.
   */
  private static int gcd(int a, int b) {
    if (a == 0) return b;
    return gcd(b % a, a);
  }

  /**
   * Get the greatest common divider of a list of integers.
   *
   * @param list list of integers.
   * @return gcd of the integers in input list.
   */
  private static int findGCD(List<Integer> list) {
    var result = 0;
    for (int element : list) {
      result = gcd(result, element);

      if (result == 1) {
        return 1;
      }
    }

    return result;
  }
}
