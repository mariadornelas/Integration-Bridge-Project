package com.faculdade.sensor;

/** Concrete Factory / Concrete Creator da família "Interno". */
public class FabricaSensoresInterno extends FabricaSensores {

    @Override
    public SensorTemperatura criarSensorTemperatura(CanalComunicacao canal) {
        return new SensorTemperaturaInterno(canal);
    }

    @Override
    public SensorPressao criarSensorPressao(CanalComunicacao canal) {
        return new SensorPressaoInterno(canal);
    }
}
