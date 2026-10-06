package com.faculdade.sensor;

/** Concrete Product A1 — ambiente controlado, limiares mais apertados (60/80). */
public class SensorTemperaturaInterno extends SensorTemperatura {

    private static final double LIMITE_ALERTA = 60.0;
    private static final double LIMITE_CRITICO = 80.0;

    public SensorTemperaturaInterno(CanalComunicacao canal) {
        super(canal);
    }

    @Override
    protected String classificar(double valorMedido) {
        if (valorMedido >= LIMITE_CRITICO) {
            return "CRITICO";
        }
        if (valorMedido >= LIMITE_ALERTA) {
            return "ALERTA";
        }
        return "NORMAL";
    }
}
