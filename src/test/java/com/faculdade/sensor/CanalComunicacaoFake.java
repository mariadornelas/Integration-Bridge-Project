package com.faculdade.sensor;

import java.util.ArrayList;
import java.util.List;

/** Dublê de teste: registra as mensagens sem imprimir nada. Não faz parte do padrão. */
class CanalComunicacaoFake implements CanalComunicacao {

    private final List<String> mensagensRecebidas = new ArrayList<>();

    @Override
    public void enviar(String mensagem) {
        mensagensRecebidas.add(mensagem);
    }

    List<String> getMensagensRecebidas() {
        return mensagensRecebidas;
    }
}
