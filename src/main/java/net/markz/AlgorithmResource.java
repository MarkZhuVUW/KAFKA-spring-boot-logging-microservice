package net.markz;

import net.markz.services.fileread.Algorithm;

import java.util.List;

public class AlgorithmResource {
  // hide constructor as we are only using the static functions from outside.
  private AlgorithmResource() {}

  public static final Algorithm bruteforce =
      configuration -> {
        var sortedConsumptionSpeeds =
            configuration.getDiskConsumptionSpeeds().stream().sorted().toList();
        long availableDiskSpace = configuration.getAvailableDiskSpace();
        int gcd = findGCD(sortedConsumptionSpeeds);

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

  private static int gcd(int a, int b) {
    if (a == 0) return b;
    return gcd(b % a, a);
  }

  private static int findGCD(List<Integer> arr) {
    var result = 0;
    for (int element : arr) {
      result = gcd(result, element);

      if (result == 1) {
        return 1;
      }
    }

    return result;
  }
}
