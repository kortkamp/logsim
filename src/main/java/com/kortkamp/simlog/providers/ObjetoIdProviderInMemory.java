package com.kortkamp.simlog.providers;

import com.kortkamp.simlog.simulator.ObjetoId;

public class ObjetoIdProviderInMemory implements ObjetoIdProvider {

    private long lastId;

    public ObjetoIdProviderInMemory(){
        this.lastId = 0;
    }

    @Override
    public ObjetoId generateNext() {
        lastId += 1;
        return new ObjetoId(lastId);
    }

}
