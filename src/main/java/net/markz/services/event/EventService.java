package net.markz.services.event;

/**
 * The EventService defines how events happen and end. It defines also how an event listener is
 * attached and detached.
 */
public interface EventService {

  /**
   * Defines the start of an event.
   *
   * @param type Event type.
   * @return an event builder.
   */
  Event.Builder startEvent(final EventType type);

  /**
   * Defines the end of an event.
   *
   * @param eventBuilder an event builder.
   */
  void endEvent(final Event.Builder eventBuilder);

  /**
   * Adds an listener
   *
   * @param listener an event listener.
   * @throws IllegalArgumentException when listener to be added is invalid.
   */
  void addEventListener(final EventListener listener);

  /**
   * Removes an event listener.
   *
   * @param listener an event listener.
   * @throws IllegalArgumentException when the listener to be removed is invalid.
   */
  void removeEventListener(final EventListener listener);
}
