package engine;

import actions.DecisaoFactory;
import actions.DecisaoStrategy;
import model.Deltas;
import model.Startup;
import persistence.StartupRepository;

import java.util.Scanner;

public class GameEngine {

    private final Startup startup;
    private final int totalRodadas;
    private final int maxDecisoesPorRodada;

    public GameEngine(Startup startup, int totalRodadas, int maxDecisoesPorRodada) {
        this.startup = startup;
        this.totalRodadas = totalRodadas;
        this.maxDecisoesPorRodada = maxDecisoesPorRodada;
    }

    public void iniciarJogo() {

        Scanner sc = new Scanner(System.in);

        for (int rodada = 1; rodada <= totalRodadas; rodada++) {
            System.out.println("\n RODADA: " + rodada);
            System.out.println(startup);

            for (int i = 1; i <= maxDecisoesPorRodada; i++) {

                System.out.println("\nEscolha uma decisão:");
                System.out.println("1 - Marketing");
                System.out.println("2 - Equipe");
                System.out.println("3 - Produto");
                System.out.println("4 - Investidores");
                System.out.println("5 - Cortar Custos");

                System.out.print("Digite o número da decisão: ");
                int escolha = sc.nextInt();

                // Converte número para texto para a factory usar
                String tipo = switch (escolha) {
                    case 1 -> "MARKETING";
                    case 2 -> "EQUIPE";
                    case 3 -> "PRODUTO";
                    case 4 -> "INVESTIDORES";
                    case 5 -> "CORTAR_CUSTOS";
                    default -> throw new IllegalArgumentException("Opção inválida");
                };

                // Cria a Strategy correspondente
                DecisaoStrategy decisao = DecisaoFactory.criar(tipo);

                // Calcula o Deltas (Strategy)
                Deltas d = decisao.aplicar(startup);

                // Aplica na startup
                startup.aplicarDeltas(d);

                System.out.println("→ Decisão aplicada: " + decisao.getNome());

                // Salvar no banco e mostrar ranking
                StartupRepository repo = new StartupRepository();
                long id = repo.salvarStartup(startup);

                System.out.println("\nStartup salva com ID: " + id);
                repo.listarRanking();
            }
        }

        sc.close();

        System.out.println("\n===== FIM DO JOGO =====");
        System.out.println(startup);
    }
}

