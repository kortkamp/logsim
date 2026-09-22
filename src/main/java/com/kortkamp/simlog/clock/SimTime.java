package com.kortkamp.simlog.clock;

public record SimTime(long seconds) implements Comparable<SimTime> {

    @Override
    public int compareTo(SimTime other) {
        return Long.compare(seconds, other.seconds);
    }

    public SimTime plusDays(int days){
        return new SimTime(this.seconds + days * 24 * 60 * 60);
    }



}