package com.faculdade.sensor;

/**
 * Ponto de encontro de três padrões:
 *
 * <ul>
 *   <li><b>Abstract Factory</b>: garante que temperatura e pressão
 *   produzidas juntas sejam sempre da mesma família (Interno/Externo).</li>
 *   <li><b>Factory Method</b>: os dois métodos de criação são abstratos
 *   aqui e só ganham corpo nas subclasses concretas; o método
 *   {@link #diagnosticar} já os usa sem saber qual família está por
 *   trás.</li>
 *   <li><b>Bridge</b>: as fábricas recebem o {@link CanalComunicacao} como
 *   parâmetro e entregam sensores já ligados a ele — a escolha da família
 *   (fábrica) e a escolha do canal são totalmente independentes.</li>
 * </ul>
 */
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
