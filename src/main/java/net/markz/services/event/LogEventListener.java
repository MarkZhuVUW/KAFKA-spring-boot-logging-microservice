package net.markz.services.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogEventListener implements EventListener {
  private static final Logger LOGGER = LogManager.getLogger(LogEventListener.class);

  @Override
  public void onEventStart(final EventType type, final Long startTime, final String uniqueId) {
    LOGGER.error("Event: {{}} with unique Id {{}} started.", type, uniqueId);
  }

  @Override
  public void onEventEnd(final Event.Builder eventBuilder) {
    var event = eventBuilder.build();
    Long duration = event.getEndTime() - event.getStartTime();
    LOGGER.error(
        "Event: {{}} with unique Id {{}} ended. Duration: {{}} nanoseconds.",
        event.getUniqueId(),
        event.getEndTime(),
        duration);
  }
}
