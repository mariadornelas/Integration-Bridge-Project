package com.faculdade.sensor;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorSensores {

    private static final GerenciadorSensores INSTANCIA = new GerenciadorSensores();

    private final Map<String, FabricaSensores> fabricasEmCache = new HashMap<>();

    private GerenciadorSensores() {
    }

    public static GerenciadorSensores getInstance() {
        return INSTANCIA;
    }

    public FabricaSensores obterFabrica(String ambiente) {
        return fabricasEmCache.computeIfAbsent(ambiente, this::resolverFabricaPorReflexao);
    }

    public int quantidadeDeFabricasCarregadas() {
        return fabricasEmCache.size();
    }

    private FabricaSensores resolverFabricaPorReflexao(String ambiente) {
        try {
            Class<?> classe = Class.forName("com.faculdade.sensor.FabricaSensores" + ambiente);
            Constructor<?> construtor = classe.getDeclaredConstructor();
            Object objeto = construtor.newInstance();
            if (!(objeto instanceof FabricaSensores)) {
                throw new IllegalArgumentException("ambiente inválido: " + ambiente);
            }
            return (FabricaSensores) objeto;
        } catch (ReflectiveOperationException ex) {
            throw new IllegalArgumentException("ambiente inexistente: " + ambiente, ex);
        }
    }
}
