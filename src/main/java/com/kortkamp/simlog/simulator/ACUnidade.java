package com.kortkamp.simlog.simulator;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.kortkamp.simlog.clock.SimTime;
import com.kortkamp.simlog.events.Event;
import com.kortkamp.simlog.events.ExpedicaoEvent;
import com.kortkamp.simlog.providers.ObjetoIdProvider;

public class ACUnidade extends Unidade {

    private LocalTime horarioDH;

    public ACUnidade(String nome, Sto sto, LocalTime horarioDH, PlanoExpedicao planoExpedicao,
            ObjetoIdProvider objetoIdProvider) {
        super(nome, sto, planoExpedicao, objetoIdProvider);
        this.horarioDH = horarioDH;
    }

    // should run on 00:00
    @Override
    public List<? extends Event> scheduleDayEvents() {

        // agenda expedição
        LocalDateTime dateTime = this.simulationClock.dateTime().plusHours(this.horarioDH.getHour())
                .plusMinutes(this.horarioDH.getMinute());
        SimTime time = this.simulationClock.toSimTime(dateTime);
        ExpedicaoEvent expedicaoEvent = new ExpedicaoEvent(this.getSto(), time);
        var ret = List.of(expedicaoEvent);
        return ret;
    }

}
