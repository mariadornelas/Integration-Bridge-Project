package com.faculdade.sensor;

public abstract class SensorPressao extends Sensor {

    protected SensorPressao(CanalComunicacao canal) {
        super(canal);
    }

    @Override
    protected String getTipo() {
        return "PRESSAO";
    }
}
