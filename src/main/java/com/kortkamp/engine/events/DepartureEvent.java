package com.kortkamp.engine.events;

import com.kortkamp.engine.Packet;

public class DepartureEvent extends Event {

    private final Packet[] packets;

    private final int departureNodeId;
    private final int arrivalNodeId;

    public DepartureEvent(double time, Packet[] packets, int departureNodeId, int arrivalNodeId) {
        this.time = time;
        this.packets = packets;
        this.departureNodeId = departureNodeId;
        this.arrivalNodeId = arrivalNodeId;
    }

    @Override
    void execute() {
        // Implement the logic for handling the departure event
       
        System.out.println( packets.length + " Packets is departing at time " + time + " from node " + departureNodeId + " to node " + arrivalNodeId);
        
        // Additional logic to handle the departure can be added here
    }

}
