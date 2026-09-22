package com.kortkamp.simlog.events;

import com.kortkamp.simlog.clock.SimTime;
import com.kortkamp.simlog.simulator.Simulator;
import com.kortkamp.simlog.simulator.Sto;
import com.kortkamp.simlog.simulator.Unidade;

public class ExpedicaoEvent extends Event {

    public ExpedicaoEvent(Sto stoUnidade, SimTime time){
        super(stoUnidade, time);
    }

    @Override
    public void execute(Simulator sim) {
        // TODO Auto-generated method stub

        Unidade unidade = sim.getUnidade(this.getSto());

        unidade.fazerExpedição();
        
    }

}
