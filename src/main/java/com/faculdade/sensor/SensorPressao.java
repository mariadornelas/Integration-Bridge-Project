package com.faculdade.sensor;

/**
 * Abstract Product B da Abstract Factory e Refined Abstraction do Bridge:
 * todo sensor de pressão, de qualquer família, se identifica como
 * "PRESSAO". Os limiares ficam nas subclasses de cada família.
 */
public abstract class SensorPressao extends Sensor {

    protected SensorPressao(CanalComunicacao canal) {
        super(canal);
    }

    @Override
    protected String getTipo() {
        return "PRESSAO";
    }
}
