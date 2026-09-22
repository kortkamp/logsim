package com.kortkamp.simlog.simulator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.kortkamp.simlog.clock.SimulationClock;
import com.kortkamp.simlog.events.Event;
import com.kortkamp.simlog.providers.ObjetoIdProvider;
import com.kortkamp.simlog.util.Logger;

public abstract class Unidade {

    private Queue<Objeto> filaExpedicao = new ArrayDeque<>();

    private Queue<Mala> malasExpedicao = new ArrayDeque<>();

    private List<Rota> rotas;

    protected Sto sto;

    protected String nome;

    protected ObjetoIdProvider objetoIdProvider;

    protected boolean isActive;

    protected SimulationClock simulationClock;

    Logger logger = Logger.getInstance();

    public Unidade(String nome, Sto sto, ObjetoIdProvider objetoIdProvider){
        this.rotas = new ArrayList<>();
        this.nome = nome;
        this.sto = sto;
        this.objetoIdProvider = objetoIdProvider;
        this.simulationClock = SimulationClock.getInstance();
        this.isActive = true;
    }

    public void adicionarRota(Rota rota){
        this.rotas.add(rota);
    }

    public boolean getIsActive(){
        return this.isActive;
    }

    public void postar(Cep cepOrigem, Cep cepDestino) {
        ObjetoId objetoId = this.objetoIdProvider.generateNext();
        this.filaExpedicao.add(
            new Objeto(
                objetoId,
                cepDestino,
                cepOrigem
            )
        );
        logger.info("Postagem " + objetoId + " para " + cepDestino.toString());
    }
            
    public void passagemInterna(Objeto objeto) {
        filaExpedicao.add(objeto);
    }
    
    public void passagemInterna(List<Objeto> filaExpedicao) {
        filaExpedicao.addAll(filaExpedicao);
    }

    public abstract List<? extends Event> startNewDay();

    public void fazerExpedição(){
        logger.debug("Expedição " + this.nome);

    }

    public void expedir(Objeto objeto, Cep destino) {

    }

    public Sto getSto() {
        return this.sto;
    }

    public String getNome(){
        return this.nome;
    }

}
