package net.markz.services.event;

public interface EventService {

  Event.Builder startEvent(final EventType type);

  void endEvent(final Event.Builder eventBuilder);

  boolean addEventListener(final EventListener listener);

  boolean removeEventListener(final EventListener listener);
}
