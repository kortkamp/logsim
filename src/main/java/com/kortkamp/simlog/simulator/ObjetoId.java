package com.kortkamp.simlog.simulator;

public class ObjetoId {
    private final long id;

    public ObjetoId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    @Override 
    public String toString(){
        return String.format("%05d", id);
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null || getClass() != obj.getClass())
    //         return false;
    //     ObjetoId geek = (ObjetoId) obj;
    //     return this.id == geek.id;
    // }
    // @Override
    // public int hashCode() {
    //     return id;
    // }
}
