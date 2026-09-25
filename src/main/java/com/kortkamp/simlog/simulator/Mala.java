package com.kortkamp.simlog.simulator;

import java.util.ArrayList;
import java.util.List;

public class Mala {

    private Sto stoDestino;
    private List<Objeto> objetos;
    private boolean aberta;

    public Mala(Sto stoDestino){
        this.stoDestino = stoDestino;
        this.objetos = new ArrayList<>();
        this.aberta = true;
    }

    public Sto getStoDestino(){
        return this.stoDestino;
    }

    public List<Objeto> getObjetos(){
        return this.objetos;
    }

    public void addObjeto(Objeto objeto){
        this.objetos.add(objeto);
    }

    public boolean isAberta(){
        return this.aberta;
    }

    public void fechar(){
        this.aberta = false;
    }

}
