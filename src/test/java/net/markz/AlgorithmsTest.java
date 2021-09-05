package net.markz;

import net.markz.services.fileread.Configuration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlgorithmsTest {

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void bruteforce1() {
    var speeds = new ArrayList<Integer>();
    speeds.add(3);
    speeds.add(7);

    Configuration config =
        new Configuration.Builder()
            .withAvailableDiskSpace(4L)
            .withDiskConsumptionSpeeds(speeds)
            .build();
    assertEquals(9, Algorithms.BRUTE_FORCE.doAlgorithm(config));
  }

  @Test
  void bruteforce2() {
    var speeds = new ArrayList<Integer>();
    speeds.add(2);
    speeds.add(4);
    speeds.add(3);
    speeds.add(6);

    Configuration config =
        new Configuration.Builder()
            .withAvailableDiskSpace(15L)
            .withDiskConsumptionSpeeds(speeds)
            .build();
    assertEquals(12, Algorithms.BRUTE_FORCE.doAlgorithm(config));
  }

  @Test
  void bruteforce3() {
    var speeds = new ArrayList<Integer>();
    speeds.add(2);
    speeds.add(4);
    speeds.add(3);
    speeds.add(6);

    Configuration config =
        new Configuration.Builder()
            .withAvailableDiskSpace(16L)
            .withDiskConsumptionSpeeds(speeds)
            .build();
    assertEquals(14, Algorithms.BRUTE_FORCE.doAlgorithm(config));
  }
}
