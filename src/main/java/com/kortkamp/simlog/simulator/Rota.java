package com.kortkamp.simlog.simulator;

import java.time.LocalTime;

public record Rota(Sto stoOrigem, Sto stoDestino, LocalTime horarioSaida, float percursoHoras) {

}
