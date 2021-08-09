package net.markz.services.fileread;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

  private Configuration config;

  @BeforeEach
  void setUp() {
    var speeds = new ArrayList<Integer>();
    speeds.add(3);
    speeds.add(7);
    config =
        new Configuration.Builder()
            .withAvailableDiskSpace(4)
            .withDiskConsumptionSpeeds(speeds)
            .build();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void getAvailableDiskSpace() {
    assertEquals(4, config.getAvailableDiskSpace());
  }

  @Test
  void getDiskConsumptionSpeeds() {
    assertEquals(3, config.getDiskConsumptionSpeeds().get(0));
    assertEquals(7, config.getDiskConsumptionSpeeds().get(1));
  }
}
