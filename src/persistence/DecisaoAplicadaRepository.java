package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Deltas;

public class DecisaoAplicadaRepository { 
    public void salvarDecisao(long startupId, long rodadaId, String nomeDecisao, Deltas d) {
    String sql = """
        INSERT INTO decisao_aplicada 
        (startup_id, rodada_id, nome_decisao, caixa_delta, reputacao_delta, moral_delta, receita_bonus_delta)
        VALUES (?, ?, ?, ?, ?, ?, ?)
    """;

    try (Connection conn = DataSourceProvider.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setLong(1, startupId);
        ps.setLong(2, rodadaId);
        ps.setString(3, nomeDecisao);
        ps.setDouble(4, d.caixaDelta());
        ps.setInt(5, d.reputacaoDelta());
        ps.setInt(6, d.moralDelta());
        ps.setDouble(7, d.bonusDelta());

        ps.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao salvar decisão aplicada", e);
    }
}

}