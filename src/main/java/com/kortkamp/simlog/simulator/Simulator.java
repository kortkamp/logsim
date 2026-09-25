package com.kortkamp.simlog.simulator;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kortkamp.simlog.clock.SimulationClock;
import com.kortkamp.simlog.events.Event;
import com.kortkamp.simlog.events.EventManager;
import com.kortkamp.simlog.events.DayStartEvent;
import com.kortkamp.simlog.events.PostagemEvent;
import com.kortkamp.simlog.events.PostagemEventsGenerator;
import com.kortkamp.simlog.providers.ObjetoIdProvider;
import com.kortkamp.simlog.providers.ObjetoIdProviderInMemory;

public class Simulator {

    private HashMap<Sto, Unidade> unidades;

    private List<Rota> rotas;

    private ObjetoIdProvider objetoIdProvider = new ObjetoIdProviderInMemory();

    private SimulationClock simulationClock;

    private PostagemEventsGenerator postagemEventsGenerator;

    private EventManager eventManager;

    public Simulator() {

        this.eventManager = EventManager.getInstance();

        this.simulationClock = SimulationClock.initialize(LocalDateTime.of(2026, 9, 21, 0, 0));

        this.postagemEventsGenerator = new PostagemEventsGenerator(simulationClock);

        this.unidades = new HashMap<>();

        this.rotas = new ArrayList<>();

        CTEUnidade cteBenfica = new CTEUnidade(
                "CTE Benfica",
                new Sto(1),
                null,
                objetoIdProvider);

        PlanoExpedicao planoExpedicaoRJ = new PlanoExpedicao(List.of(
                new PlanoExpedicaoItem(
                    new CepRange(0, 99999999), cteBenfica.getSto()
                )));

        ACUnidade acAperibe = new ACUnidade(
                "AC Aperibé",
                new Sto(2),
                LocalTime.of(9, 0),
                planoExpedicaoRJ,
                objetoIdProvider);

        ACUnidade acItaocara = new ACUnidade(
                "AC Itaocara",
                new Sto(3),
                LocalTime.of(10, 0),
                planoExpedicaoRJ,
                objetoIdProvider);

        addUnidade(acAperibe);
        addUnidade(acItaocara);
        addUnidade(cteBenfica);

        Rota rota1 = new Rota(cteBenfica.getSto(), cteBenfica.getSto(), LocalTime.of(2, 0));
        rota1.addPontoParada(new RotaItem(cteBenfica.getSto(), 0.5f));
        rota1.addPontoParada(new RotaItem(acAperibe.getSto(), 7.5f));
        rota1.addPontoParada(new RotaItem(acItaocara.getSto(), 8f));
        rota1.addPontoParada(new RotaItem(cteBenfica.getSto(), 18f));

        addRota(rota1);

        this.startNewDay();
    }

    private void addUnidade(Unidade unidade) {
        unidades.put(unidade.getSto(), unidade);
    }

    private void addRota(Rota rota){
        this.rotas.add(rota);
    }

    public Unidade getUnidade(Sto unidadeSto) {
        return this.unidades.get(unidadeSto);
    }

    public SimulationClock getSimulationClock() {
        return this.simulationClock;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    public void startNewDay() {

        // should run every day at 00:00

        LocalDateTime currentDateTime = this.simulationClock.dateTime();

        List<PostagemEvent> postagens = this.postagemEventsGenerator.generateLinear(
                10,
                currentDateTime.withHour(9),
                currentDateTime.withHour(17),
                new CepRange(01000000, 99999999),
                new CepRange(28495000, 28495999),
                new Sto(2));

        // eventManager.scheduleMany(postagens);
        for (Event event : postagens) {
            eventManager.schedule(event);
        }

        // List<Event> unidadesDaylyEvents = new ArrayList<>();
        
        for (Map.Entry<Sto, Unidade> entry : unidades.entrySet()) {
            this.eventManager.scheduleMany(entry.getValue().scheduleDayEvents());
        }

        for(Rota rota : rotas){
            this.eventManager.scheduleMany(rota.scheduleDayEvents());
        }

        eventManager.schedule(new DayStartEvent(this.simulationClock.now().plusDays(1)));

    }

    public void run(int days) {
        while (this.eventManager.hasEvents()) {

            if (!this.simulationClock.dateTime().isBefore(this.simulationClock.simulationStart().plusDays(days))) {
                break;
            }

            Event event = this.eventManager.nextEvent();

            this.simulationClock.advanceTo(event.geTime());

            event.execute(this);

        }
    }
}
