package com.kortkamp.simlog.events;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.kortkamp.simlog.clock.SimTime;
import com.kortkamp.simlog.clock.SimulationClock;
import com.kortkamp.simlog.providers.RandomCepProvider;
import com.kortkamp.simlog.simulator.CepRange;
import com.kortkamp.simlog.simulator.Sto;

public class PostagemEventsGenerator {

    private SimulationClock simulationClock;

    private RandomCepProvider randomCepProvider;

    public PostagemEventsGenerator(SimulationClock simulationClock){
        this.simulationClock = simulationClock;
        this.randomCepProvider = new RandomCepProvider();
    }

    public List<PostagemEvent> generateLinear(int ammount, SimTime fromTime, SimTime toTime, CepRange cepOrigemRange, CepRange cepDestinoRange , Sto stoUnidade){
        float step = (toTime.seconds() - fromTime.seconds())/(ammount - 1);
        return IntStream.range(0, ammount)
        .mapToObj(a -> new PostagemEvent(
            stoUnidade,
            new SimTime(fromTime.seconds() + Math.round(a * step)),
            randomCepProvider.generateFromRange(cepOrigemRange), 
            randomCepProvider.generateFromRange(cepDestinoRange)
           ))
        .collect(Collectors.toList());
    }

    public List<PostagemEvent> generateLinear(int ammount, LocalDateTime fromDateTime, LocalDateTime toDateTime, CepRange cepOrigemRange, CepRange cepDestinoRange , Sto stoUnidade){
        return generateLinear(
            ammount, 
            simulationClock.toSimTime(fromDateTime), 
            simulationClock.toSimTime(toDateTime), 
            cepOrigemRange, 
            cepDestinoRange, 
            stoUnidade);
    }


}
