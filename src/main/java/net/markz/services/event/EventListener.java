package net.markz.services.event;

public interface EventListener {
  void onEventStart(final EventType type, final long startTime, final String uniqueId);

  void onEventEnd(final Event.Builder eventBuilder);
}
