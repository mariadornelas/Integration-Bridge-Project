package com.faculdade.sensor;

public abstract class SensorTemperatura extends Sensor {

    protected SensorTemperatura(CanalComunicacao canal) {
        super(canal);
    }

    @Override
    protected String getTipo() {
        return "TEMPERATURA";
    }
}
