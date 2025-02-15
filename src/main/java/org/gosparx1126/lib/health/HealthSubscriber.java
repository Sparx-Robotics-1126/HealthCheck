package org.gosparx1126.lib.health;

/**
 * A health subscriber receives all
 * messages posted to a health topic.
 */
public interface HealthSubscriber {
    public void receive(HealthTopic topic, HealthEvent event);
}
