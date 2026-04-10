package dao;
import entities.*;
import infra.DataBaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {
    public void inserir(Consulta c) {

        String sql = "INSERT INTO consulta (id, id_beneficiario, id_dentista, data_hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, c.getId());
            stmt.setLong(2, c.getBeneficiario().getId());
            stmt.setLong(3, c.getDentista().getId());
            stmt.setTimestamp(4, Timestamp.valueOf(c.getDataHora()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir consulta", e);
        }
    }
    public List<Consulta> listar() {

        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM consulta";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Consulta c = new Consulta(
                        new Beneficiario(rs.getLong("id_beneficiario"), "", "", "", "", ""),
                        new Dentista(rs.getLong("id_dentista"), "", null, null, "", ""),
                        rs.getTimestamp("data_hora").toLocalDateTime()
                );

                c.setId(rs.getLong("id"));
                lista.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas", e);
        }

        return lista;
    }
    public void atualizar(Consulta c) {

        String sql = "UPDATE consulta SET id_beneficiario=?, id_dentista=?, data_hora=? WHERE id=?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, c.getBeneficiario().getId());
            stmt.setLong(2, c.getDentista().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(c.getDataHora()));
            stmt.setLong(4, c.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar consulta", e);
        }
    }
    public void deletar(Long id) {

        String sql = "DELETE FROM consulta WHERE id=?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar consulta", e);
        }
    }
}