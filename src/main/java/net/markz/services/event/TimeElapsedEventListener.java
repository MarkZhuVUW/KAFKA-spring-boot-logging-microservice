package net.markz.services.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An event listener responsible for logging the elapsed time between the start and end of an event.
 */
public class TimeElapsedEventListener implements EventListener {
  private static final Logger LOGGER = LogManager.getLogger(TimeElapsedEventListener.class);

  @Override
  public void onEventStart(final EventType type, final long startTime, final String uniqueId) {
    LOGGER.info("Event: {{}} with unique Id {{}} started.", type, uniqueId);
  }

  @Override
  public void onEventEnd(final Event.Builder eventBuilder) {
    var event = eventBuilder.build();
    long duration = event.getEndTime() - event.getStartTime();
    LOGGER.info(
        "Event: {{}} with unique Id {{}} ended. Duration: {{}} nanoseconds.",
        event.getUniqueId(),
        event.getEndTime(),
        duration);
  }
}
