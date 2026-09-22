package com.kortkamp.simlog.events;

import com.kortkamp.simlog.clock.SimTime;
import com.kortkamp.simlog.simulator.Objeto;
import com.kortkamp.simlog.simulator.Simulator;
import com.kortkamp.simlog.simulator.Sto;

public class DepartureEvent extends Event {

    private final Objeto[] objetos;

    private final int departureNodeId;
    private final int arrivalNodeId;

    public DepartureEvent(Sto stoUnidade, SimTime time, Objeto[] objetos, int departureNodeId, int arrivalNodeId) {
        super(stoUnidade, time);
        this.objetos = objetos;
        this.departureNodeId = departureNodeId;
        this.arrivalNodeId = arrivalNodeId;
    }

    @Override
    public void execute(Simulator sim) {

        
        // Implement the logic for handling the departure event
       
        System.out.println( objetos.length + " objetos is departing at time " + this.geTime() + " from node " + departureNodeId + " to node " + arrivalNodeId);
        
        // Additional logic to handle the departure can be added here
    }

}
