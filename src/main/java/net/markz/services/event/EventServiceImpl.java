package net.markz.services.event;

import java.util.*;

/** The {@link EventServiceImpl} is an non-extensible implementation */
public class EventServiceImpl implements EventService {

  private final Set<EventListener> eventListeners;

  public EventServiceImpl() {
    this.eventListeners = new HashSet<>();
  }

  @Override
  public Event.Builder startEvent(final EventType type) {

    var startTime = System.nanoTime();
    var uniqueId = UUID.randomUUID().toString();
    var eventBuilder =
        new Event.Builder().withType(type).withStartTime(startTime).withUniqueId(uniqueId);
    eventListeners.forEach(listener -> listener.onEventStart(type, startTime, uniqueId));
    return eventBuilder;
  }

  @Override
  public void endEvent(final Event.Builder eventBuilder) {
    var endTime = System.nanoTime();
    eventBuilder.withEndTime(endTime);
    eventListeners.forEach(listener -> listener.onEventEnd(eventBuilder));
  }

  @Override
  public void addEventListener(final EventListener listener) {
    if (!this.eventListeners.add(listener)) {
      throw new IllegalArgumentException("Listener cannot be attached");
    }
  }

  @Override
  public void removeEventListener(final EventListener listener) {
    if (!this.eventListeners.remove(listener)) {
      throw new IllegalArgumentException("Listener cannot be removed");
    }
  }
}
