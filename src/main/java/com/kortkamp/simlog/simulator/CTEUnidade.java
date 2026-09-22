package com.kortkamp.simlog.simulator;

import java.util.ArrayList;
import java.util.List;

import com.kortkamp.simlog.events.Event;
import com.kortkamp.simlog.providers.ObjetoIdProvider;

public class CTEUnidade extends Unidade{

    public CTEUnidade(String nome, Sto sto, ObjetoIdProvider objetoIdProvider) {
        super(nome, sto, objetoIdProvider);
    }

    @Override
    public List<? extends Event> startNewDay() {
        return new ArrayList<>();
    }

}
