package com.kortkamp.simlog.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kortkamp.simlog.simulator.Rota;
import com.kortkamp.simlog.simulator.RotaItem;
import com.kortkamp.simlog.simulator.Sto;

public class RotasRepository {

    private List<Rota> rotas;

    private Map<RotaItemKey, Integer> rotaItens;

    public RotasRepository() {
        this.rotas = new ArrayList<>();
        this.rotaItens = new HashMap<>();
    }

    public void addRota(Rota rota) {
        this.rotas.add(rota);
    }

    public void addRotaItem(RotaItem rotaItem) {
        this.rotaItens.put(new RotaItemKey(rotaItem.stoOrigem(), rotaItem.stoDestino()), rotaItem.percursoMinutos());
    }

    public void addRotaItem(Sto stoOrigem, Sto stoDestino, Integer percursoMinutos) {
        this.rotaItens.put(new RotaItemKey(stoOrigem, stoDestino), percursoMinutos);
    }

    public RotaItem getRotaItemBySto(Sto stoOrigem, Sto stoDestino) {
        var percursoMinutos = rotaItens.get(new RotaItemKey(stoOrigem, stoDestino));

        if (percursoMinutos != null) {
            return new RotaItem(stoOrigem, stoDestino, percursoMinutos);
        }

        return null;
    }

}
