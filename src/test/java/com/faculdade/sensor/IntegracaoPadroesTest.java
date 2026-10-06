package com.faculdade.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegracaoPadroesTest {

    private final GerenciadorSensores gerenciador = GerenciadorSensores.getInstance();

    @Test
    void todasAsCombinacoesDeAmbienteTipoECanalDevemFuncionar() {
        int combinacoes = 0;

        for (String ambiente : new String[] { "Interno", "Externo" }) {
            FabricaSensores fabrica = gerenciador.obterFabrica(ambiente);

            for (int i = 0; i < 2; i++) {
                CanalComunicacaoFake canal = new CanalComunicacaoFake();
                Sensor[] sensores = { fabrica.criarSensorTemperatura(canal), fabrica.criarSensorPressao(canal) };

                for (Sensor sensor : sensores) {
                    assertDoesNotThrow(() -> sensor.monitorar(50.0));
                    combinacoes++;
                }
                assertEquals(2, canal.getMensagensRecebidas().size());
            }
        }

        assertEquals(8, combinacoes);
    }

    @Test
    void ambienteDeveMudarAClassificacaoEOCanalNaoDeve() {
        FabricaSensores interno = gerenciador.obterFabrica("Interno");
        FabricaSensores externo = gerenciador.obterFabrica("Externo");
        CanalComunicacaoFake canal = new CanalComunicacaoFake();

        assertEquals("ALERTA", interno.criarSensorTemperatura(canal).monitorar(70.0));
        assertEquals("NORMAL", externo.criarSensorTemperatura(canal).monitorar(70.0));
    }

    @Test
    void canalDeveMudarODestinoEOAmbienteNaoDeve() {
        FabricaSensores fabrica = gerenciador.obterFabrica("Interno");
        CanalComunicacaoFake canalA = new CanalComunicacaoFake();
        CanalComunicacaoFake canalB = new CanalComunicacaoFake();

        String statusA = fabrica.criarSensorTemperatura(canalA).monitorar(70.0);
        String statusB = fabrica.criarSensorTemperatura(canalB).monitorar(70.0);

        assertEquals(statusA, statusB);
        assertEquals(1, canalA.getMensagensRecebidas().size());
        assertEquals(1, canalB.getMensagensRecebidas().size());
    }

    @Test
    void sensorCriadoPelaFabricaDevePoderTrocarDeCanalEmTempoDeExecucao() {
        CanalComunicacaoFake antigo = new CanalComunicacaoFake();
        CanalComunicacaoFake novo = new CanalComunicacaoFake();
        Sensor sensor = gerenciador.obterFabrica("Externo").criarSensorPressao(antigo);

        sensor.monitorar(5.0);
        sensor.trocarCanal(novo);
        sensor.monitorar(5.0);

        assertEquals(1, antigo.getMensagensRecebidas().size());
        assertEquals(1, novo.getMensagensRecebidas().size());
    }

    @Test
    void fluxoCompletoComCanaisReaisDeveFuncionar() {
        CanalDisplayLocal display = new CanalDisplayLocal();
        CanalRedeRemota rede = new CanalRedeRemota();

        gerenciador.obterFabrica("Interno").diagnosticar(display, 95.0, 11.0);
        gerenciador.obterFabrica("Externo").diagnosticar(rede, 95.0, 11.0);

        assertEquals(2, display.getMensagensEnviadas().size());
        assertEquals(2, rede.getMensagensEnviadas().size());
        assertTrue(display.getMensagensEnviadas().get(0).contains("CRITICO"));
        assertTrue(rede.getMensagensEnviadas().get(0).contains("ALERTA"));
    }
}
