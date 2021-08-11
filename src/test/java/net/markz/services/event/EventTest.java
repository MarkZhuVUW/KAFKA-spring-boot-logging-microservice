package net.markz.services.event;

import ch.qos.logback.core.joran.event.EndEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
  private Event event;

  @BeforeEach
  void setUp() {
    event =
        new Event.Builder()
            .withEndTime(1L)
            .withStartTime(2L)
            .withType(EventType.FILE_READ_TIME_ELAPSED)
            .withValue(1)
            .withUniqueId("123")
            .build();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void getStartTime() {
    assertEquals(2L, event.getStartTime());
  }

  @Test
  void getEndTime() {
    assertEquals(1L, event.getEndTime());
  }

  @Test
  void getUniqueId() {
    assertEquals("123", event.getUniqueId());
  }

  @Test
  void getType() {
    assertEquals(EventType.FILE_READ_TIME_ELAPSED, event.getType());
  }

  @Test
  void testEqualsFalse() {
    assertNotEquals(event, new Event.Builder()
            .withUniqueId("1")
            .withType(EventType.FILE_READ_TIME_ELAPSED)
            .build());
  }

  @Test
  void testEqualsTrue() {
    assertEquals(event, new Event.Builder()
            .withUniqueId("123")
            .withType(EventType.FILE_READ_TIME_ELAPSED)
            .build());
  }
}
