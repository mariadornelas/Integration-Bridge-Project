package com.faculdade.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanaisComunicacaoTest {

    @Test
    void displayLocalDeveUsarSeuPrefixoEAcumularHistorico() {
        CanalDisplayLocal canal = new CanalDisplayLocal();

        canal.enviar("primeira");
        canal.enviar("segunda");

        assertEquals(2, canal.getMensagensEnviadas().size());
        assertTrue(canal.getMensagensEnviadas().get(0).startsWith("[DISPLAY LOCAL]"));
    }

    @Test
    void redeRemotaDeveUsarSeuPrefixoEAcumularHistorico() {
        CanalRedeRemota canal = new CanalRedeRemota();

        canal.enviar("primeira");
        canal.enviar("segunda");

        assertEquals(2, canal.getMensagensEnviadas().size());
        assertTrue(canal.getMensagensEnviadas().get(0).startsWith("[REDE REMOTA]"));
    }

    @Test
    void historicoDevolvidoPorAmbosOsCanaisDeveSerSomenteLeitura() {
        CanalDisplayLocal display = new CanalDisplayLocal();
        CanalRedeRemota rede = new CanalRedeRemota();
        display.enviar("x");
        rede.enviar("x");

        assertThrows(UnsupportedOperationException.class, () -> display.getMensagensEnviadas().add("y"));
        assertThrows(UnsupportedOperationException.class, () -> rede.getMensagensEnviadas().add("y"));
    }
}
