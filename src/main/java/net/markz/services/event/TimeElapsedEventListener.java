package net.markz.services.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An event listener responsible for logging the elapsed time between the start and end of an event.
 */
public class TimeElapsedEventListener implements EventListener {
  private static final Logger LOGGER = LogManager.getLogger(TimeElapsedEventListener.class);

  /**
   * On event start handler which basically logs the start of the event.
   *
   * @param type event type
   * @param startTime start time of event.
   * @param uniqueId unique id of event.
   */
  @Override
  public void onEventStart(final EventType type, final long startTime, final String uniqueId) {
    LOGGER.info("Event: {{}} with unique Id {{}} started.", type, uniqueId);
  }

  /**
   * On event end handler. calculates duration and logs the end of the event with duration. We could
   * push the event with duration to ElasticSearch for later use.
   *
   * @param eventBuilder event builder.
   */
  @Override
  public void onEventEnd(final Event.Builder eventBuilder) {

    var event = eventBuilder.build();
    long duration = event.getEndTime() - event.getStartTime();
    if (event.getValue() != null && event.getType() == EventType.FILE_READ_TIME_ELAPSED) {
      LOGGER.info("Remaining time before OOM: {{}} seconds", event.getValue());
    }
    LOGGER.info(
        "Event: {{}} with unique Id {{}} ended. Duration: {{}} nanoseconds.",
        event.getType(),
        event.getUniqueId(),
        duration);
  }

  /**
   * I don't want to have duplicate Listener objects here so all listeners who are instance of
   * TimeElapsedEventListener are considered equal.
   *
   * @param o input object.
   * @return whether input object and this are equal.
   */
  @Override
  public boolean equals(Object o) {
    return o instanceof TimeElapsedEventListener;
  }

  @Override
  public int hashCode() {
    return TimeElapsedEventListener.class.hashCode();
  }
}
