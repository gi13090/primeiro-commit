package dao;
import entities.Doador;
import infra.DataBaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoadorDAO {
    public void inserir(Doador d) {

        String sql = "INSERT INTO doador (id, nome, endereco, email, contato, data_doacao, valor_doado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, d.getId());
            stmt.setString(2, d.getNome());
            stmt.setString(3, d.getEndereco());
            stmt.setString(4, d.getEmail());
            stmt.setString(5, d.getContato().getTelefone());
            stmt.setDate(6, Date.valueOf(d.getData()));
            stmt.setBigDecimal(7, d.getValorDoado());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir doador", e);
        }
    }
    public List<Doador> listar() {
        List<Doador> lista = new ArrayList<>();
        String sql = "SELECT * FROM doador";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Doador d = new Doador(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("contato"),
                        rs.getDate("data_doacao").toLocalDate(),
                        rs.getBigDecimal("valor_doado")
                );

                lista.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar doadores", e);
        }
        return lista;
    }
    public void atualizar(Doador d) {

        String sql = "UPDATE doador SET nome=?, endereco=?, email=?, contato=?, data_doacao=?, valor_doado=? WHERE id=?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, d.getNome());
            stmt.setString(2, d.getEndereco());
            stmt.setString(3, d.getEmail());
            stmt.setString(4, d.getContato().getTelefone());
            stmt.setDate(5, Date.valueOf(d.getData()));
            stmt.setBigDecimal(6, d.getValorDoado());
            stmt.setLong(7, d.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar doador", e);
        }
    }
    public void deletar(Long id) {
        String sql = "DELETE FROM doador WHERE id=?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar doador", e);
        }
    }
}