package com.faculdade.sensor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CanalDisplayLocal implements CanalComunicacao {

    private final List<String> mensagensEnviadas = new ArrayList<>();

    @Override
    public void enviar(String mensagem) {
        String formatada = "[DISPLAY LOCAL] " + mensagem;
        System.out.println(formatada);
        mensagensEnviadas.add(formatada);
    }

    public List<String> getMensagensEnviadas() {
        return Collections.unmodifiableList(mensagensEnviadas);
    }
}
