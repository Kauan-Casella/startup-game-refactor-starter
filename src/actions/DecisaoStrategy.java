package actions;

import model.Deltas;
import model.Startup;

public interface DecisaoStrategy {

    // A decisão calcula os efeitos sobre a startup e devolve um Deltas
    Deltas aplicar(Startup s);

    // Opcional (para undo/comando)
    default void reverter(Startup s, Deltas d) { /* opcional */ }

    // Nome da decisão (pra mostrar no menu, logs etc.)
    String getNome();
}
