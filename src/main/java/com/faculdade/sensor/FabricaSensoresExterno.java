package com.faculdade.sensor;

/** Concrete Factory / Concrete Creator da família "Externo". */
public class FabricaSensoresExterno extends FabricaSensores {

    @Override
    public SensorTemperatura criarSensorTemperatura(CanalComunicacao canal) {
        return new SensorTemperaturaExterno(canal);
    }

    @Override
    public SensorPressao criarSensorPressao(CanalComunicacao canal) {
        return new SensorPressaoExterno(canal);
    }
}
