package com.faculdade.sensor;

/**
 * Cliente de demonstração. Só conhece {@link GerenciadorSensores},
 * {@link FabricaSensores}, {@link Sensor} e {@link CanalComunicacao}:
 * nenhuma classe concreta de fábrica, sensor ou canal de comunicação
 * aparece como tipo de variável, a não ser para ler o histórico do canal.
 */
public class App {

    public static void main(String[] args) {
        GerenciadorSensores gerenciador = GerenciadorSensores.getInstance();
        FabricaSensores interno = gerenciador.obterFabrica("Interno");
        FabricaSensores externo = gerenciador.obterFabrica("Externo");

        CanalDisplayLocal display = new CanalDisplayLocal();
        CanalRedeRemota rede = new CanalRedeRemota();

        System.out.println("=== Ambiente Interno via Display Local ===");
        System.out.println(interno.diagnosticar(display, 70.0, 7.0));

        System.out.println();
        System.out.println("=== Ambiente Externo via Rede Remota (mesmos valores, outros limiares) ===");
        System.out.println(externo.diagnosticar(rede, 70.0, 7.0));

        System.out.println();
        System.out.println("=== Troca de canal em tempo de execução (Bridge) ===");
        Sensor sensor = interno.criarSensorTemperatura(display);
        sensor.monitorar(85.0);
        sensor.trocarCanal(rede);
        sensor.monitorar(85.0);

        System.out.println();
        System.out.println("Mensagens no display: " + display.getMensagensEnviadas().size());
        System.out.println("Mensagens na rede: " + rede.getMensagensEnviadas().size());
        System.out.println("Fábrica Interno reaproveitada do cache? "
                + (interno == gerenciador.obterFabrica("Interno")));
        System.out.println("GerenciadorSensores é Singleton? "
                + (gerenciador == GerenciadorSensores.getInstance()));
    }
}
