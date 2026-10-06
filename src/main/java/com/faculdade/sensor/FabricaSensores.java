package com.faculdade.sensor;

public abstract class FabricaSensores {

    public abstract SensorTemperatura criarSensorTemperatura(CanalComunicacao canal);

    public abstract SensorPressao criarSensorPressao(CanalComunicacao canal);

    public final String diagnosticar(CanalComunicacao canal, double valorTemperatura, double valorPressao) {
        String statusTemperatura = criarSensorTemperatura(canal).monitorar(valorTemperatura);
        String statusPressao = criarSensorPressao(canal).monitorar(valorPressao);
        return String.format(
                "Temperatura=%s (%.1f) | Pressao=%s (%.1f)",
                statusTemperatura, valorTemperatura, statusPressao, valorPressao);
    }
}
