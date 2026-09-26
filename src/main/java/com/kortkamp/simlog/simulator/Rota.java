package com.kortkamp.simlog.simulator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.kortkamp.simlog.clock.SimulationClock;
import com.kortkamp.simlog.events.CargaDescargaEvent;
import com.kortkamp.simlog.events.Event;

public class Rota implements SimulationEntity {

    private Sto stoOrigem;
    private Sto stoDestino;
    private LocalTime horarioSaida;
    private List<RotaItem> paradas;

    protected SimulationClock simulationClock;

    public Rota(Sto stoOrigem, Sto stoDestino, LocalTime horarioSaida, List<RotaItem> paradas) {
        this.stoOrigem = stoOrigem;
        this.stoDestino = stoDestino;
        this.horarioSaida = horarioSaida;
        this.paradas = paradas;
        this.simulationClock = SimulationClock.getInstance();
    }

    public Rota(Sto stoOrigem, Sto stoDestino, LocalTime horarioSaida) {
        this(stoOrigem, stoDestino, horarioSaida, new ArrayList<>());
    }

    public void addPontoParada(RotaItem item) {
        this.paradas.add(item);
    }

    public Sto getStoOrigem() {
        return stoOrigem;
    }

    public Sto getStoDestino() {
        return stoDestino;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

    public List<RotaItem> getParadas() {
        return paradas;
    }

    public boolean permiteEnvio(Sto stoDestino) {
        return paradas
                .stream()
                .anyMatch(paradaItem -> paradaItem.stoDestino().equals(stoDestino));
    }

    @Override
    public List<? extends Event> scheduleDayEvents() {

        return this.paradas
                .stream()
                .map(item -> new CargaDescargaEvent(
                        item.stoDestino(),
                        this,
                        simulationClock.toSimTime(
                                simulationClock.dateTime()
                                        .plusHours(this.horarioSaida.getHour()).plusMinutes(this.horarioSaida.getMinute())
                                        // .plusMinutes(((int) (item.tempoPercursoHoras() * 60)))
                                    )))
                .toList();

    }
}
