package net.markz.services.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO Alert when
public class AlertEventListener implements EventListener {

  private static final Logger LOGGER = LogManager.getLogger(AlertEventListener.class);

  @Override
  public void onEventStart(final EventType type, final long startTime, final String uniqueId) {
    //    LOGGER.debug(msg);
  }

  @Override
  public void onEventEnd(Event.Builder eventBuilder) {
    //    LOGGER.debug(event);
  }
}
