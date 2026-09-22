package com.kortkamp.simlog.simulator;

public record Cep(int value) {

    @Override 
    public String toString(){
        String str = String.format("%08d", value);
        return str.substring(0, 5) + "-" + str.substring(5);
    }
}
