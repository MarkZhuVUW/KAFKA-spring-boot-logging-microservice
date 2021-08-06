package net.markz.services.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO implement singleton pattern for all the impls.
public class AlertEventListenerImpl implements EventListener {

  private static final Logger LOGGER = LogManager.getLogger(AlertEventListenerImpl.class);

  @Override
  public void onEventStart(final EventType type, final long startTime, final String uniqueId) {
    //    LOGGER.debug(msg);
  }

  @Override
  public void onEventEnd(Event.Builder eventBuilder) {
    //    LOGGER.debug(event);
  }
}
