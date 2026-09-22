package com.kortkamp.simlog.clock;

import java.time.LocalDateTime;

public interface SimulationClockInterface {

    SimTime now();

    LocalDateTime dateTime();

    void advanceTo(SimTime time);

    SimTime toSimTime(LocalDateTime dateTime);

    LocalDateTime simulationStart();
}
