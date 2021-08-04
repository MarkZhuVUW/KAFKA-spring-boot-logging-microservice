package net.markz.services.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogEventListener implements EventListener {
  private static final Logger LOGGER = LogManager.getLogger(LogEventListener.class);

  @Override
  public void onEventStart(final EventType type, final Long startTime, final String uniqueId) {
    var logMsg = String.format("Event: {%s} with unique Id {%s} started", type, uniqueId);
    LOGGER.trace(logMsg);
  }

  @Override
  public void onEventEnd(final Event.Builder eventBuilder) {
    var event = eventBuilder.withEndTime(System.currentTimeMillis()).build();
    Long duration = event.getEndTime() - event.getStartTime();
    var msg =
        String.format(
            "Event: {%s} with unique Id {%s} ended. Duration: {%s}",
            event.getUniqueId(), event.getEndTime(), duration);
    LOGGER.trace(msg);
  }
}
