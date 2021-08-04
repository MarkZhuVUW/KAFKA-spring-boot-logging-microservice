package net.markz.services.event;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EventServiceImpl implements EventService {

  private final Set<EventListener> eventListeners;

  public EventServiceImpl() {
    this.eventListeners = Collections.newSetFromMap(new ConcurrentHashMap<>());
  }

  @Override
  public Event.Builder startEvent(final EventType type) {
    var startTime = System.currentTimeMillis();
    var uniqueId = UUID.randomUUID().toString();
    var eventBuilder =
        new Event.Builder().withType(type).withStartTime(startTime).withUniqueId(uniqueId);
    eventListeners.forEach(listener -> listener.onEventStart(type, startTime, uniqueId));
    return eventBuilder;
  }

  @Override
  public void endEvent(final Event.Builder eventBuilder) {
    eventListeners.forEach(listener -> listener.onEventEnd(eventBuilder));
  }

  @Override
  public boolean addEventListener(final EventListener listener) {
    return this.eventListeners.add(listener);
  }

  @Override
  public boolean removeEventListener(final EventListener listener) {
    return this.eventListeners.remove(listener);
  }
}
