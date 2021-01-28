package com.ercarts.clock.domain;

/**
 * @author dkyryk
 */
public enum ClockType {
    QUARTZ, MECHANICAL;

    public static ClockType resolveFromLabel(String label) {
        switch (label) {
            case "Q": return QUARTZ;
            case "M": return MECHANICAL;
            default:
                throw new IllegalArgumentException("Unrecognized label " + label);
        }
    }
}