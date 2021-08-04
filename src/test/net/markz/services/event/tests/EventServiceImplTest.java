package net.markz.services.event.tests;

import net.markz.services.event.EventServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class EventServiceImplTest {

  private EventServiceImpl eventServiceImpl;

  @BeforeEach
  void setUp() {
    this.eventServiceImpl = new EventServiceImpl();
  }

  @AfterEach
  void tearDown() {
    this.eventServiceImpl = null;
  }

  @Test
  void testStartEvent() {
    // Given
    //    this.eventServiceImpl.startEvent(EventType.FILE_READ_LOG);
  }

  @Test
  void testEndEvent() {}

  @Test
  void testAddEventListener() {}

  @Test
  void testRemoveEventListener() {}
}
