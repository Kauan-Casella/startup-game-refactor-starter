package ui;

import config.Config;
import engine.GameEngine;
import model.Startup;
import model.vo.Dinheiro;
import model.vo.Humor;
import persistence.StartupRepository;

import java.util.Scanner;

public class ConsoleApp {

    private final Config config = new Config();

    public void start() {

        Scanner sc = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("         STARTUP GAME - CONSOLE     ");
        System.out.println("====================================");

        System.out.println("total.rodadas=" + config.totalRodadas());
        System.out.println("max.decisoes.por.rodada=" + config.maxDecisoesPorRodada());
        System.out.println();

        while (true) {
            System.out.println("==== MENU ====");
            System.out.println("1 - Criar novo jogo");
            System.out.println("2 - Continuar jogo (em breve)");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {

                case 1 -> {
                    System.out.print("Digite o nome da sua startup: ");
                    String nomeStartup = sc.nextLine();

                    
                    Startup startup = new Startup(
                            nomeStartup,
                            new Dinheiro(100000.0),  // caixa inicial
                            new Dinheiro(3000.0),   // receita base inicial
                            new Humor(10),          // reputação inicial
                            new Humor(10)           // moral inicial
                    );

                    GameEngine engine = new GameEngine(
                            startup,
                            config.totalRodadas(),
                            config.maxDecisoesPorRodada()
                    );

                    System.out.println("\nJogo criado com sucesso! Iniciando...\n");

                    engine.iniciarJogo();

                    System.out.println("\n=== FIM DO JOGO ===");
                    System.out.println(startup);
                    System.out.println();

                    StartupRepository repo = new StartupRepository();
                    repo.listarRanking();

                }

                case 2 -> {
                    System.out.println("\n⚠   Continuação de jogo ainda não implementada.");
                    System.out.println("    Use 'Novo jogo' por enquanto.\n");
                }

                case 3 -> {
                    System.out.println("\nSaindo do jogo. Obrigado por jogar!");
                    sc.close();
                    return;
                }

                default -> System.out.println("\nOpção inválida!\n");
            }
        }
    }
}
