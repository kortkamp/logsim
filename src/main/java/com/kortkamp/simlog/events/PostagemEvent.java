package com.kortkamp.simlog.events;

import com.kortkamp.simlog.clock.SimTime;
import com.kortkamp.simlog.simulator.Cep;
import com.kortkamp.simlog.simulator.Simulator;
import com.kortkamp.simlog.simulator.Sto;
import com.kortkamp.simlog.simulator.Unidade;

public class PostagemEvent extends Event {

    private Cep cepOrigem;

    private Cep cepDestino;

    private Sto stoUnidade;
    
    public PostagemEvent(Sto stoUnidade,  SimTime time,  Cep cepDestino, Cep cepOrigem){
        super(stoUnidade, time);
        this.cepOrigem = cepOrigem;
        this.cepDestino = cepDestino;
        this.stoUnidade = stoUnidade;
    }

    @Override
    public void execute(Simulator sim) {

        Unidade unidade = sim.getUnidade(this.getSto());

        if(unidade.getIsActive()){
            unidade.postar(this.cepOrigem, this.cepDestino);
        }
    }

    public Sto getSto(){
        return this.stoUnidade;
    }
    
}
