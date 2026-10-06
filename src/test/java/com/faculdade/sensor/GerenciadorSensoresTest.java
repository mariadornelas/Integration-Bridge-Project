package com.faculdade.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GerenciadorSensoresTest {

    private final GerenciadorSensores gerenciador = GerenciadorSensores.getInstance();

    @Test
    void getInstanceDeveSempreDevolverAMesmaReferencia() {
        assertSame(GerenciadorSensores.getInstance(), GerenciadorSensores.getInstance());
    }

    @Test
    void deveResolverAsDuasFamiliasPorReflexao() {
        assertInstanceOf(FabricaSensoresInterno.class, gerenciador.obterFabrica("Interno"));
        assertInstanceOf(FabricaSensoresExterno.class, gerenciador.obterFabrica("Externo"));
    }

    @Test
    void mesmoAmbientePedidoDuasVezesDeveReaproveitarAFabricaDoCache() {
        assertSame(gerenciador.obterFabrica("Interno"), gerenciador.obterFabrica("Interno"));
    }

    @Test
    void ambientesDiferentesDevemProduzirFabricasDiferentes() {
        assertNotSame(gerenciador.obterFabrica("Interno"), gerenciador.obterFabrica("Externo"));
    }

    @Test
    void ambienteInexistenteDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class, () -> gerenciador.obterFabrica("Subterraneo"));
    }

    @Test
    void nomeQueApontaParaClasseAbstrataDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class, () -> gerenciador.obterFabrica(""));
    }
}
