package com.kortkamp.simlog;

import com.kortkamp.simlog.repositories.RotasRepository;
import com.kortkamp.simlog.simulator.Simulator;

/**
 * Hello world!
 */
public class App {

    
    
    //this.engine.run()

    public void execute(){

    }

    public static void main(String[] args) {

        RotasRepository rotasRepository = new RotasRepository();

        System.out.println("Simulador Correios");
        final Simulator engine = new Simulator(rotasRepository);
        engine.run(5);
    }
}
