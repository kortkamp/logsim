package com.kortkamp.simlog.events;

import com.kortkamp.simlog.clock.SimTime;
import com.kortkamp.simlog.simulator.Simulator;

public class DayStartEvent extends Event{

    public DayStartEvent(SimTime time){
        super(null, time);
    }

    @Override
    public void execute(Simulator sim) {
        sim.startNewDay();
    }

}
