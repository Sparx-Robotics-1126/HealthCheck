package org.gosparx1126.lib.health;

import java.util.HashMap;
import java.util.Optional;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The health manager acts as a simple container
 * for all topics that are displayed on the Elastic
 * dashboard.
 */
public class HealthManager {
    private HashMap<String, HealthTopic> topics;

    public HealthManager() {
        topics = new HashMap<>();
    }

    public Optional<HealthTopic> getTopic(String name) {
        if (topics.containsKey(name)) {
            return Optional.of(topics.get(name));
        }

        return Optional.empty();
    }

    public void registerTopic(HealthTopic topic) {
        topics.put(topic.name(), topic);
        SmartDashboard.putData(topic);
    }
}
