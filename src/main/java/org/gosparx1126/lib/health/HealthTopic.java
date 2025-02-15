package org.gosparx1126.lib.health;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;

/**
 * A health topic encompasses a discrete unit to
 * be measured on the robot (e.g. a subsystem).
 */
public class HealthTopic implements Sendable {
    private String name;
    private HealthEvent headline;
    private List<HealthEvent> log;
    private List<HealthSubscriber> subscribers;

    public HealthTopic(String name) {
        this.name = name;
        headline = null;
        log = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    public String name() {
        return name;
    }

    public void report(HealthEvent event) {
        for (HealthSubscriber subscriber : subscribers) {
            subscriber.receive(this, event);
        }

        switch (event.status()) {
            case FAILURE:
            case GOOD:
                headline = event;
            case INFO:
            default:
                log.add(event);
        }
    }

    public void subscribe(HealthSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof HealthTopic
            && ((HealthTopic) o).name().equals(this.name());
    }

	@Override
	public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType(name());
        builder.addStringProperty("Current", () -> headline.toString(), null);
        builder.addStringArrayProperty("Log", () -> log.stream()
            .map(HealthEvent::toString)
            .toArray(String[]::new),
        null);
	}
}
