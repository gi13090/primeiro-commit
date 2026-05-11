package dao;
import entities.Beneficiario;
import infra.DataBaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeneficiarioDAO {
    public void inserir(Beneficiario b) {

        String sql = "INSERT INTO beneficiario (id, nome, cpf, telefone, email, endereco) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, b.getId());
            stmt.setString(2, b.getNome());
            stmt.setString(3, b.getCpf());
            stmt.setString(4, b.getContato().getTelefone());
            stmt.setString(5, b.getEmail());
            stmt.setString(6, b.getEndereco());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir beneficiario", e);
        }
    }
    public List<Beneficiario> listar() {
        List<Beneficiario> lista = new ArrayList<>();
        String sql = "SELECT * FROM beneficiario";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Beneficiario b = new Beneficiario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco")
                );

                lista.add(b);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar beneficiarios", e);
        }

        return lista;
    }
    public Beneficiario buscarPorId(Long id) {
        String sql = "SELECT * FROM BENEFICIARIO WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Beneficiario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar beneficiário", e);
        }

        return null;
    }

    public void atualizar(Beneficiario b) {

        String sql = "UPDATE beneficiario SET nome=?, cpf=?, telefone=?, email=?, endereco=? WHERE id=?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, b.getNome());
            stmt.setString(2, b.getCpf());
            stmt.setString(3, b.getContato().getTelefone());
            stmt.setString(4, b.getEmail());
            stmt.setString(5, b.getEndereco());
            stmt.setLong(6, b.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar beneficiario", e);
        }
    }
    public void deletar(Long id) {

        String sql = "DELETE FROM beneficiario WHERE id=?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar beneficiario", e);
        }
    }
}