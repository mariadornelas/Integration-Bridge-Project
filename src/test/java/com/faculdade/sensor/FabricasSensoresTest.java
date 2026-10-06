package com.faculdade.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Abstract Factory (consistência de família) + Factory Method (criação delegada às subclasses). */
class FabricasSensoresTest {

    private final CanalComunicacaoFake canal = new CanalComunicacaoFake();

    @Test
    void fabricaInternoDeveProduzirApenasProdutosDaFamiliaInterno() {
        FabricaSensores fabrica = new FabricaSensoresInterno();

        assertInstanceOf(SensorTemperaturaInterno.class, fabrica.criarSensorTemperatura(canal));
        assertInstanceOf(SensorPressaoInterno.class, fabrica.criarSensorPressao(canal));
    }

    @Test
    void fabricaExternoDeveProduzirApenasProdutosDaFamiliaExterno() {
        FabricaSensores fabrica = new FabricaSensoresExterno();

        assertInstanceOf(SensorTemperaturaExterno.class, fabrica.criarSensorTemperatura(canal));
        assertInstanceOf(SensorPressaoExterno.class, fabrica.criarSensorPressao(canal));
    }

    @Test
    void cadaChamadaDeveDevolverUmSensorNovo() {
        FabricaSensores fabrica = new FabricaSensoresInterno();

        assertNotSame(fabrica.criarSensorTemperatura(canal), fabrica.criarSensorTemperatura(canal));
    }

    @Test
    void sensorCriadoDeveJaNascerLigadoAoCanalRecebido() {
        FabricaSensores fabrica = new FabricaSensoresExterno();

        fabrica.criarSensorPressao(canal).monitorar(5.0);

        assertEquals(1, canal.getMensagensRecebidas().size());
    }

    @Test
    void diagnosticarDeveUsarOsDoisFactoryMethodsEEnviarDuasMensagens() {
        FabricaSensores fabrica = new FabricaSensoresInterno();

        String diagnostico = fabrica.diagnosticar(canal, 95.0, 5.0);

        assertTrue(diagnostico.contains("CRITICO"));
        assertTrue(diagnostico.contains("NORMAL"));
        assertEquals(2, canal.getMensagensRecebidas().size());
    }
}
