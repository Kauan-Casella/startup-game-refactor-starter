package persistence;

import model.Startup;

import java.sql.*;

public class RodadaRepository {

    public long salvarRodada(long startupId, int numeroRodada, Startup s) {
        String sql = """
            INSERT INTO rodada (startup_id, numero_da_rodada, caixa, receita_base, reputacao, moral)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, startupId);
            ps.setInt(2, numeroRodada);
            ps.setDouble(3, s.getCaixa().valor());
            ps.setDouble(4, s.getReceitaBase().valor());
            ps.setInt(5, s.getReputacao().valor());
            ps.setInt(6, s.getMoral().valor());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar rodada", e);
        }

        return -1;
    }
}
