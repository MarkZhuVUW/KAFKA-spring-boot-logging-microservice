package net.markz.services.fileread;

import net.markz.AlgorithmResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
// TODO no time!
@RunWith(MockitoJUnitRunner.class)
class FileReadingServiceTest {

  //  private final FileReadingServiceImpl fileReadingServiceImpl;

  //    @Mock private final Algorithm algo;

  @BeforeEach
  void setUp() {
    //    fileReadingServiceImpl = FileReadingServiceImpl.getInstance();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void readLineIntoConfig() {}

  @Test
  void calculateETA() {
    //    // Given
    //    List<Integer> diskConsumptionSpeeds = new ArrayList<>();
    //    diskConsumptionSpeeds.add(3);
    //    diskConsumptionSpeeds.add(7);
    //    Configuration configuration =
    //        new Configuration.Builder()
    //            .withAvailableDiskSpace(4)
    //            .withDiskConsumptionSpeeds(diskConsumptionSpeeds)
    //            .build();
    //    when(AlgorithmResource.BRUTE_FORCE.doAlgorithm(configuration)).thenReturn(9L);
    //    // When
    //    long eta = fileReadingServiceImpl.calculateETA(AlgorithmResource.BRUTE_FORCE,
    // configuration);
    //    verify(algo.doAlgorithm(configuration), times(1));
    //    assertEquals(9L, eta);
  }
}
