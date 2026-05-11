package dao;
import entities.Dentista;
import infra.DataBaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DentistaDAO {
    public void inserir(Dentista d) {

        String sql = "INSERT INTO dentista (id, nome, cro) VALUES (?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, d.getId());
            stmt.setString(2, d.getNome());
            stmt.setString(3, d.getCro());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dentista", e);
        }
    }

    public List<Dentista> listar() {

        List<Dentista> lista = new ArrayList<>();
        String sql = "SELECT * FROM dentista";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Dentista d = new Dentista(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        null,
                        null,
                        null,
                        rs.getString("cro")
                );
                lista.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar dentistas", e);
        }

        return lista;
    }

    public Dentista buscarPorId(Long id) {
        String sql = "SELECT * FROM DENTISTA WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Dentista(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("cro")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar dentista", e);
        }

        return null;
    }

    public void deletar(Long id) {

        String sql = "DELETE FROM dentista WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar dentista", e);
        }
    }
    public void atualizar(Dentista d) {

        String sql = "UPDATE dentista SET nome = ?, cro = ? WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, d.getNome());
            stmt.setString(2, d.getCro());
            stmt.setLong(3, d.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dentista", e);
        }
    }
}