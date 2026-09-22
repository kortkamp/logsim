package com.kortkamp.simlog.providers;

import java.util.Random;

import com.kortkamp.simlog.simulator.Cep;
import com.kortkamp.simlog.simulator.CepRange;

public class RandomCepProvider {

    private  Random random = new Random();

    private final int  MAXCEP = 99999999;

    private final int MINCEP = 1000000;

    public Cep generate(){
        return new Cep(random.nextInt(MAXCEP-MINCEP)+MINCEP);
    }

    public Cep generateFromRange(CepRange range){
        return new Cep(random.nextInt(range.max().value()-range.min().value())+range.min().value());
    }
}
