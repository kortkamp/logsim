package com.kortkamp.simlog.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.kortkamp.simlog.clock.SimulationClock;
import com.kortkamp.simlog.events.Event;
import com.kortkamp.simlog.events.EventManager;
import com.kortkamp.simlog.events.ImportacaoEvent;
import com.kortkamp.simlog.providers.ObjetoIdProvider;
import com.kortkamp.simlog.util.Logger;

public abstract class Unidade implements SimulationEntity {

    private List<Objeto> filaExpedicao = new ArrayList<>();

    private List<Mala> malasExpedicao = new ArrayList<>();

    private List<Mala> malasImportacao = new ArrayList<>();

    private List<Rota> rotas;

    private PlanoExpedicao planoExpedicao;

    protected Sto sto;

    protected String nome;

    protected boolean isActive;

    protected ObjetoIdProvider objetoIdProvider;

    protected SimulationClock simulationClock;

    protected EventManager eventManager;

    Logger logger = Logger.getInstance();

    public Unidade(String nome, Sto sto, PlanoExpedicao planoExpedicao, ObjetoIdProvider objetoIdProvider) {
        this.rotas = new ArrayList<>();
        this.nome = nome;
        this.sto = sto;
        this.planoExpedicao = planoExpedicao;
        this.objetoIdProvider = objetoIdProvider;
        this.simulationClock = SimulationClock.getInstance();
        this.eventManager = EventManager.getInstance();
        this.isActive = true;
    }

    public void definirPlanoExpedicao(PlanoExpedicao planoExpedicao) {
        this.planoExpedicao = planoExpedicao;
    }

    public void adicionarRota(Rota rota) {
        this.rotas.add(rota);
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void postar(Cep cepOrigem, Cep cepDestino) {

        Objeto objeto = new Objeto(
                this.objetoIdProvider.generateNext(),
                cepDestino,
                cepOrigem);

        PlanoExpedicaoItem itemExpedicao = planoExpedicao.items()
                .stream()
                .filter(
                        a -> a.faixaCep().isBetween(cepDestino))
                .findAny()
                .orElseThrow();

        var malaExists = malasExpedicao
                .stream()
                .filter(mala -> mala.getStoDestino() == itemExpedicao.stoUnidade() &&
                        mala.isAberta())
                .findAny();

        if (malaExists.isPresent()) {
            malaExists.get().addObjeto(objeto);
        } else {
            Mala mala = new Mala(itemExpedicao.stoUnidade());
            mala.addObjeto(objeto);
            malasExpedicao.add(
                    mala);

        }

        // Mala malaExists = malasExpedicao.stream().anyMatch(a -> a.);

        // this.filaExpedicao.add(objeto);

        // logger.debug("Postagem " + objeto.id() + " para " + cepDestino.toString());
    }

    public void passagemInterna(Objeto objeto) {
        filaExpedicao.add(objeto);
    }

    public void passagemInterna(List<Objeto> filaExpedicao) {
        filaExpedicao.addAll(filaExpedicao);
    }

    public abstract List<? extends Event> scheduleDayEvents();

    public void fazerExpedição() {
        logger.debug("Expedição " + this.nome);

        if (malasExpedicao.isEmpty()) {
            logger.debug("Sem malas para expedir");
            return;
        }

        for (Mala mala : malasExpedicao) {
            mala.fechar();
            RotaItem rotaItem = this.rotasRepository.ge
            ImportacaoEvent importacaoEvent = new ImportacaoEvent(sto, null, null)
        }

    }

    // public void expedir(Objeto objeto, Cep destino) {
    // }

    public List<Mala> fazerCarga(Rota rota) {

        List<Mala> paraCarregar = malasExpedicao
                .stream()
                .filter(
                        mala -> !mala.isAberta() &&
                                rota.permiteEnvio(mala.getStoDestino()))
                .toList();

        Map<Boolean, List<Mala>> result = malasExpedicao
                .stream()
                .collect(Collectors.partitioningBy(
                        mala1 -> rota.permiteEnvio(mala1.getStoDestino())));

        this.malasExpedicao = result.get(false);

        return result.get(true);
    }

    public void fazerDescarga(List<Mala> malas) {
        this.malasImportacao.addAll(malas);
    }

    public Sto getSto() {
        return this.sto;
    }

    public String getNome() {
        return this.nome;
    }

}
