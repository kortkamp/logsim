package com.kortkamp.simlog.clock;

import java.time.Duration;
import java.time.LocalDateTime;

public class SimulationClock {

    private LocalDateTime start;

    private SimTime currentTime;

    public static volatile SimulationClock instance;

    public static synchronized SimulationClock initialize(LocalDateTime start) {

        if (instance != null) {
            throw new IllegalStateException("SimulationClock already initialized");
        }

        instance = new SimulationClock(start);

        return instance;
    }

    private SimulationClock(LocalDateTime start) {
        this.start = start;
        this.currentTime = new SimTime(0);
    }

    public static SimulationClock getInstance() {
        if (instance == null) {

            throw new IllegalStateException("SimulationClock not initialized");

        }
        return instance;
    }

    // @Override
    public SimTime now() {
        return currentTime;
    }

    // @Override
    public LocalDateTime dateTime() {
        return start.plusSeconds(currentTime.seconds());
    }

    // @Override
    public void advanceTo(SimTime time) {
        if (time.seconds() < currentTime.seconds()) {
            throw new IllegalArgumentException(
                    "Simulation time cannot move backwards");
        }

        currentTime = time;
    }

    // @Override
    public SimTime toSimTime(LocalDateTime dateTime) {

        return new SimTime(
                Duration.between(start, dateTime).getSeconds());

    }

    // @Override
    public LocalDateTime simulationStart() {
        return this.start;
    }
}
