package net.markz;

import net.markz.services.event.Event;
import net.markz.services.event.EventListener;
import net.markz.services.event.EventService;
import net.markz.services.event.EventType;
import net.markz.services.fileread.FileReadingService;
import net.markz.services.fileread.FileReadingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
// TODO no time!
@RunWith(MockitoJUnitRunner.class)
class ResourceProviderTest {

  @Mock private EventService eventService;
  @Mock private FileReadingService fileReadingService;
  @Mock private Set<EventListener> eventListeners;

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void calculateAllETAs() {
    // Given
    //    var eventBuilder = new Event.Builder();
    //    when(this.eventService.startEvent(any()))
    //        .thenReturn(
    //            new Event.Builder().withUniqueId("1").withType(EventType.FILE_READ_TIME_ELAPSED));
    //    doNothing().when(this.eventService.endEvent(eventBuilder));

  }
}
