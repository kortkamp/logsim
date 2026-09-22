package com.kortkamp.simlog.simulator;

public record CepRange(Cep min, Cep max) {
    public CepRange(int min, int max){
        this(new Cep(min), new Cep(max));
    }

}
