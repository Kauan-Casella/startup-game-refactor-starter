package persistence;

import model.Startup;
import java.sql.*;

public class StartupRepository {

    /**
     * Salva a startup no banco de dados e retorna o ID gerado automaticamente.
     */
    public long salvarStartup(Startup s) {
        String sql = """
            INSERT INTO startup (nome, caixa, receita_base, reputacao, moral, score_final)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, s.getNome());
            ps.setDouble(2, s.getCaixa().valor());
            ps.setDouble(3, s.getReceitaBase().valor());
            ps.setInt(4, s.getReputacao().valor());
            ps.setInt(5, s.getMoral().valor());
            ps.setDouble(6, s.scoreFinal());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar Startup no banco", e);
        }

        return -1; // fallback
    }

    /**
     * Lista todas as startups ordenadas por score, criando o ranking final.
     */
    public void listarRanking() {
        String sql = """
            SELECT nome, score_final
            FROM startup
            ORDER BY score_final DESC
            """;

        try (Connection conn = DataSourceProvider.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n===== RANKING DE STARTUPS =====");

            int posicao = 1;
            while (rs.next()) {
                String nome = rs.getString("nome");
                double score = rs.getDouble("score_final");

                System.out.printf("%dº lugar – %s – Score: %.2f%n",
                        posicao++, nome, score);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar ranking", e);
        }
    }
}
