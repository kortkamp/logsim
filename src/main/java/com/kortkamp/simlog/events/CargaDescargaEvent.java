package com.kortkamp.simlog.events;

import com.kortkamp.simlog.clock.SimTime;
import com.kortkamp.simlog.simulator.Rota;
import com.kortkamp.simlog.simulator.Simulator;
import com.kortkamp.simlog.simulator.Sto;
import com.kortkamp.simlog.simulator.Unidade;
import com.kortkamp.simlog.util.Logger;

public class CargaDescargaEvent extends Event {

    private Rota rota;

    private Logger logger = Logger.getInstance();

    public CargaDescargaEvent(Sto stoUnidade, Rota rota, SimTime time) {
        super(stoUnidade, time);
        this.rota = rota;

    }

    @Override
    public void execute(Simulator sim) {

        Unidade unidade = sim.getUnidade(this.getSto());

        var a = unidade.fazerCarga(this.rota);

        logger.debug( "STO " + this.getSto().getValue() + " "  +  a.size() + " malas expedidas");

    }
}
