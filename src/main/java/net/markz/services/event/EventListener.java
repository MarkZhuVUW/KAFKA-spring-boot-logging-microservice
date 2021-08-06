package net.markz.services.event;

/** An EventListener defines what happens when the start or end of an event is observe. */
public interface EventListener {
  /**
   * On event start observed.
   *
   * @param type event type
   * @param startTime start time of event.
   * @param uniqueId unique id of event.
   */
  void onEventStart(final EventType type, final long startTime, final String uniqueId);

  /**
   * On event end observed.
   *
   * @param eventBuilder event builder.
   */
  void onEventEnd(final Event.Builder eventBuilder);
}
