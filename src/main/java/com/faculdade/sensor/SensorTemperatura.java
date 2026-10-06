package com.faculdade.sensor;

/**
 * Abstract Product A da Abstract Factory e Refined Abstraction do Bridge:
 * todo sensor de temperatura, de qualquer família, se identifica como
 * "TEMPERATURA". Os limiares ficam nas subclasses de cada família.
 */
public abstract class SensorTemperatura extends Sensor {

    protected SensorTemperatura(CanalComunicacao canal) {
        super(canal);
    }

    @Override
    protected String getTipo() {
        return "TEMPERATURA";
    }
}
