package engine;

import actions.DecisaoFactory;
import actions.DecisaoStrategy;
import model.Deltas;
import model.Startup;
import persistence.DecisaoAplicadaRepository;
import persistence.RodadaRepository;
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

        StartupRepository startupRepo = new StartupRepository();
        RodadaRepository rodadaRepo = new RodadaRepository();
        DecisaoAplicadaRepository decisaoRepo = new DecisaoAplicadaRepository();

        long startupId = startupRepo.salvarStartup(startup);

        Scanner sc = new Scanner(System.in);

        for (int rodada = 1; rodada <= totalRodadas; rodada++) {
            System.out.println("\n RODADA: " + rodada);
            System.out.println(startup);

            // Salvar a rodada
            long rodadaId = rodadaRepo.salvarRodada(startupId, rodada, startup);

            for (int i = 1; i <= maxDecisoesPorRodada; i++) {

                System.out.println("\nEscolha uma decisão:");
                System.out.println("1 - Marketing");
                System.out.println("2 - Equipe");
                System.out.println("3 - Produto");
                System.out.println("4 - Investidores");
                System.out.println("5 - Cortar Custos");

                System.out.print("Digite o número da decisão: ");
                int escolha = sc.nextInt();

                String tipo = switch (escolha) {
                    case 1 -> "MARKETING";
                    case 2 -> "EQUIPE";
                    case 3 -> "PRODUTO";
                    case 4 -> "INVESTIDORES";
                    case 5 -> "CORTAR_CUSTOS";
                    default -> throw new IllegalArgumentException("Opção inválida");
                };

                DecisaoStrategy decisao = DecisaoFactory.criar(tipo);
                Deltas d = decisao.aplicar(startup);

                startup.aplicarDeltas(d);

                System.out.println("→ Decisão aplicada: " + decisao.getNome());

                decisaoRepo.salvarDecisao(startupId, rodadaId, decisao.getNome(), d);
            }
        }

        sc.close();

        System.out.println("\n===== FIM DO JOGO =====");
        System.out.println(startup);
    }
}
