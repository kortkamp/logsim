package com.kortkamp.simlog.simulator;

public class Sto {
    private int value;

    public Sto(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Sto geek = (Sto) obj;
        return this.value == geek.value;
    }
    @Override
    public int hashCode() {
        return value;
    }

}
