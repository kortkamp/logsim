package com.kortkamp.engine;

abstract class Event implements Comparable<Event> {

    double time;

    @Override 
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
    
    abstract void execute();
}
