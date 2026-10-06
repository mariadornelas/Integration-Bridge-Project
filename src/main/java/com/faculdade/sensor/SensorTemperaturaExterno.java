package com.faculdade.sensor;

public class SensorTemperaturaExterno extends SensorTemperatura {

    private static final double LIMITE_ALERTA = 80.0;
    private static final double LIMITE_CRITICO = 100.0;

    public SensorTemperaturaExterno(CanalComunicacao canal) {
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
