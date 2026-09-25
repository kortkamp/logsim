package com.kortkamp.simlog.simulator;

import java.util.List;

import com.kortkamp.simlog.events.Event;

public interface SimulationEntity {

    //responsável por agendar os eventos sob sua responsabilidade para o dia
    List<? extends Event> scheduleDayEvents();
}
