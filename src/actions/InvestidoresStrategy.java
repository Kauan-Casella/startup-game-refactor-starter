package actions;

import model.Deltas;
import model.Startup;

public class InvestidoresStrategy implements DecisaoStrategy {

    @Override
    public Deltas aplicar(Startup startup) {
        // Só calcula o efeito e devolve o Deltas
        return new Deltas(
            +20000.0,  // caixaDelta -> gasta em marketing
            +5,       // reputacaoDelta -> melhora visibilidade
            +1,       // moralDelta -> equipe animada
            +10000.0   // bonusDelta -> aumenta receita base
        );
    }

    @Override
    public String getNome() {
        return "Marketing";
    }
}
