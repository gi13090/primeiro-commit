package dao;

import entities.Consulta;
import entities.Beneficiario;
import entities.Dentista;
import enums.Prioridade;
import enums.StatusAgendamento;
import infra.DataBaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private final BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
    private final DentistaDAO dentistaDAO = new DentistaDAO();

    // CREATE
    public void inserir(Consulta consulta) {
        String sql = "INSERT INTO CONSULTA (id, id_beneficiario, id_dentista, data_hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, consulta.getId());
            stmt.setLong(2, consulta.getBeneficiario().getId());
            stmt.setLong(3, consulta.getDentista().getId());
            stmt.setTimestamp(4, Timestamp.valueOf(consulta.getDataHora()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir consulta", e);
        }
    }

    public List<Consulta> listarTodos() {
        List<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM CONSULTA";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Long idBeneficiario = rs.getLong("id_beneficiario");
                Long idDentista = rs.getLong("id_dentista");

                Beneficiario b = beneficiarioDAO.buscarPorId(idBeneficiario);
                Dentista d = dentistaDAO.buscarPorId(idDentista);

                Consulta c = new Consulta(
                        b,
                        d,
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        Prioridade.MEDIA,
                        StatusAgendamento.AGENDADO
                );

                c.setId(rs.getLong("id"));

                lista.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas", e);
        }

        return lista;
    }

    public Consulta buscarPorId(Long id) {
        String sql = "SELECT * FROM CONSULTA WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Long idBeneficiario = rs.getLong("id_beneficiario");
                Long idDentista = rs.getLong("id_dentista");

                Beneficiario b = beneficiarioDAO.buscarPorId(idBeneficiario);
                Dentista d = dentistaDAO.buscarPorId(idDentista);
                Consulta c = new Consulta(
                        b,
                        d,
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        Prioridade.MEDIA,
                        StatusAgendamento.AGENDADO
                );

                c.setId(rs.getLong("id"));

                return c;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar consulta", e);
        }

        return null;
    }

    public void atualizar(Consulta consulta) {
        String sql = "UPDATE CONSULTA SET id_beneficiario = ?, id_dentista = ?, data_hora = ? WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, consulta.getBeneficiario().getId());
            stmt.setLong(2, consulta.getDentista().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(consulta.getDataHora()));
            stmt.setLong(4, consulta.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar consulta", e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM CONSULTA WHERE id = ?";

        try (Connection conn = DataBaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar consulta", e);
        }
    }
}