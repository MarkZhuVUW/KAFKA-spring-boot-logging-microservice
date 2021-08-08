package net.markz.services.event;

import java.util.*;

/**
 * The EventServiceImpl is a non-extensible implementation of {@link EventService}. It keeps a set
 * of {@link EventListener} that perform actions according to the specs of the {@link EventListener}
 * interface. Singleton pattern is used here as logically there should only be one EventServiceImpl
 * object in the program.
 */
public final class EventServiceImpl implements EventService {

  private final Set<EventListener> eventListeners;
  private static EventServiceImpl singleton;

  // Encapsulate constructor here for the singleton pattern.
  private EventServiceImpl() {
    this.eventListeners = new HashSet<>();
  }

  // Singleton pattern. Lazy initialize an EventServiceImpl class here.
  public static EventServiceImpl getInstance() {
    if (singleton == null) {
      singleton = new EventServiceImpl();
    }
    return singleton;
  }

  /**
   * Executes the on event end listener callbacks with relevant information.
   *
   * @param type Event type.
   * @return
   */
  @Override
  public Event.Builder startEvent(final EventType type) {

    var startTime = System.nanoTime();
    var uniqueId = UUID.randomUUID().toString();
    var eventBuilder =
        new Event.Builder().withType(type).withStartTime(startTime).withUniqueId(uniqueId);
    eventListeners.forEach(listener -> listener.onEventStart(type, startTime, uniqueId));
    return eventBuilder;
  }

  /**
   * Executes the on event end listener callbacks with relevant information.
   *
   * @param eventBuilder an event builder.
   */
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
