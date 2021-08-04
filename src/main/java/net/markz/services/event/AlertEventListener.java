package net.markz.services.event;

import net.markz.services.event.Event;
import net.markz.services.event.EventListener;
import net.markz.services.event.EventType;
import net.markz.services.event.LogEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AlertEventListener implements EventListener {

  private static final Logger LOGGER = LogManager.getLogger(LogEventListener.class);

  @Override
  public void onEventStart(final EventType type, final Long startTime, final String uniqueId) {
    //    LOGGER.debug(msg);
  }

  @Override
  public void onEventEnd(Event.Builder eventBuilder) {
    //    LOGGER.debug(event);
  }
}
