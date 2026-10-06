package com.faculdade.sensor;

/**
 * Implementor do Bridge: contrato comum a qualquer forma de transmitir a
 * leitura de um sensor. Os sensores só conhecem esta interface, nunca um
 * canal concreto.
 */
public interface CanalComunicacao {
    void enviar(String mensagem);
}
