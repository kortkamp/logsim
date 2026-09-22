package com.kortkamp.simlog.events;

import com.kortkamp.simlog.clock.SimTime;
import com.kortkamp.simlog.simulator.Simulator;
import com.kortkamp.simlog.simulator.Sto;

public abstract class Event implements Comparable<Event> {

    private SimTime time;

    private Sto stoUnidade;

    public Event(Sto stoUnidade , SimTime time){
        this.stoUnidade = stoUnidade;
        this.time = time;
    }

    public Sto getSto(){
        return stoUnidade;
    }

    @Override 
    public int compareTo(Event other) {
        return time.compareTo(other.time);
    }

    public SimTime geTime(){
        return this.time;
    }
    
    public abstract void execute(Simulator sim);
}
