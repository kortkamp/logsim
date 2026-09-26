package com.kortkamp.simlog.events;

import java.util.List;

import com.kortkamp.simlog.clock.SimTime;
import com.kortkamp.simlog.simulator.Mala;
import com.kortkamp.simlog.simulator.Simulator;
import com.kortkamp.simlog.simulator.Sto;
import com.kortkamp.simlog.util.Logger;

public class ImportacaoEvent extends Event {

    private List<Mala> malas;

    private Logger logger = Logger.getInstance();


    public ImportacaoEvent(Sto stoUnidade, List<Mala> malas, SimTime time) {
        super(stoUnidade, time);
        this.malas = malas;
    }

    @Override
    public void execute(Simulator sim) {
        logger.debug("Chegada de " + this.malas.size() + " em " + this.getSto());
    }

}
