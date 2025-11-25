package actions;

import model.Deltas;
import model.Startup;

public class MarketingStrategy implements DecisaoStrategy {

    @Override
    public Deltas aplicar(Startup startup) {
        // Só calcula o efeito e devolve o Deltas
        return new Deltas(
            -1500.0,  // caixaDelta -> gasta em marketing
            +5,       // reputacaoDelta -> melhora visibilidade
            +1,       // moralDelta -> equipe animada
            +300.0   // bonusDelta -> aumenta receita base
        );
    }

    @Override
    public String getNome() {
        return "Marketing";
    }
}
