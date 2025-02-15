package org.gosparx1126.lib.health;

/**
 * A health event is a single data point from a
 * reporting subsystem.
 */
public class HealthEvent {
    private final HealthDisposition status;
    private final String msg;

    public HealthEvent(HealthDisposition status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static HealthEvent good(String msg) {
        return new HealthEvent(HealthDisposition.GOOD, msg);
    }

    public static HealthEvent fail(String msg) {
        return new HealthEvent(HealthDisposition.FAILURE, msg);
    }

    public static HealthEvent info(String msg) {
        return new HealthEvent(HealthDisposition.INFO, msg);
    }

    public HealthDisposition status() {
        return status;
    }

    public String msg() {
        return msg;
    }

    @Override
    public String toString() {
        return status().name() + ": " + msg();
    }
}
