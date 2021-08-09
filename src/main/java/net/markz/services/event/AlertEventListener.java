package net.markz.services.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO An AlertEventListener that pushes dangerous ETA of out of disk space to ElasticSearch for
// TODO continuous monitoring. However I have not got time to finish it.
public class AlertEventListener implements EventListener {

  private static final Logger LOGGER = LogManager.getLogger(AlertEventListener.class);

  @Override
  public void onEventStart(final EventType type, final long startTime, final String uniqueId) {
    //    LOGGER.debug(msg);
  }

  @Override
  public void onEventEnd(Event.Builder eventBuilder) {
    //    LOGGER.debug(event);
    // push ETA to ElasticSearch
  }
}
