package com.kortkamp.simlog;

import com.kortkamp.simlog.simulator.Simulator;

/**
 * Hello world!
 */
public class App {

    
    
    //this.engine.run()

    public void execute(){

    }

    public static void main(String[] args) {
        System.out.println("Simulador Correios");
        final Simulator engine = new Simulator();
        engine.run(5);
    }
}
