package com.faculdade.sensor;

/** Concrete Product B2 — ambiente exposto ao tempo, mais tolerante (10/14). */
public class SensorPressaoExterno extends SensorPressao {

    private static final double LIMITE_ALERTA = 10.0;
    private static final double LIMITE_CRITICO = 14.0;

    public SensorPressaoExterno(CanalComunicacao canal) {
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
