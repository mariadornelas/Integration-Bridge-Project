package com.faculdade.sensor;

public class SensorPressaoInterno extends SensorPressao {

    private static final double LIMITE_ALERTA = 6.0;
    private static final double LIMITE_CRITICO = 10.0;

    public SensorPressaoInterno(CanalComunicacao canal) {
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
