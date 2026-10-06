package com.faculdade.sensor;

import java.util.Objects;

public abstract class Sensor {

    protected CanalComunicacao canal;

    protected Sensor(CanalComunicacao canal) {
        this.canal = Objects.requireNonNull(canal, "canal não pode ser nulo");
    }

    public final String monitorar(double valorMedido) {
        String status = classificar(valorMedido);
        canal.enviar(String.format("%s valor=%.1f status=%s", getTipo(), valorMedido, status));
        return status;
    }

    public final void trocarCanal(CanalComunicacao novoCanal) {
        this.canal = Objects.requireNonNull(novoCanal, "canal não pode ser nulo");
    }

    protected abstract String classificar(double valorMedido);

    protected abstract String getTipo();
}
