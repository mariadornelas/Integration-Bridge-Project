package com.faculdade.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SensoresConcretosTest {

    private final CanalComunicacaoFake canal = new CanalComunicacaoFake();

    @Test
    void temperaturaInternoDeveRespeitarSeusLimiares() {
        Sensor sensor = new SensorTemperaturaInterno(canal);

        assertEquals("NORMAL", sensor.monitorar(50.0));
        assertEquals("ALERTA", sensor.monitorar(65.0));
        assertEquals("CRITICO", sensor.monitorar(95.0));
    }

    @Test
    void temperaturaExternoDeveRespeitarSeusLimiares() {
        Sensor sensor = new SensorTemperaturaExterno(canal);

        assertEquals("NORMAL", sensor.monitorar(70.0));
        assertEquals("ALERTA", sensor.monitorar(85.0));
        assertEquals("CRITICO", sensor.monitorar(105.0));
    }

    @Test
    void pressaoInternoDeveRespeitarSeusLimiares() {
        Sensor sensor = new SensorPressaoInterno(canal);

        assertEquals("NORMAL", sensor.monitorar(5.0));
        assertEquals("ALERTA", sensor.monitorar(7.0));
        assertEquals("CRITICO", sensor.monitorar(11.0));
    }

    @Test
    void pressaoExternoDeveRespeitarSeusLimiares() {
        Sensor sensor = new SensorPressaoExterno(canal);

        assertEquals("NORMAL", sensor.monitorar(9.0));
        assertEquals("ALERTA", sensor.monitorar(11.0));
        assertEquals("CRITICO", sensor.monitorar(15.0));
    }

    @Test
    void cadaMonitoramentoDeveEnviarUmaMensagemComTipoEStatus() {
        new SensorTemperaturaInterno(canal).monitorar(50.0);
        new SensorPressaoExterno(canal).monitorar(15.0);

        assertEquals(2, canal.getMensagensRecebidas().size());
        assertTrue(canal.getMensagensRecebidas().get(0).contains("TEMPERATURA"));
        assertTrue(canal.getMensagensRecebidas().get(0).contains("NORMAL"));
        assertTrue(canal.getMensagensRecebidas().get(1).contains("PRESSAO"));
        assertTrue(canal.getMensagensRecebidas().get(1).contains("CRITICO"));
    }

    @Test
    void naoDeveAceitarCanalNulo() {
        assertThrows(NullPointerException.class, () -> new SensorTemperaturaInterno(null));
    }
}
