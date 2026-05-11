package entities;

import enums.Prioridade;
import enums.StatusAgendamento;

import java.time.LocalDateTime;

public class Consulta {

    private Long id;
    private Beneficiario beneficiario;
    private Dentista dentista;
    private LocalDateTime dataHora;
    private Prioridade prioridade;
    private StatusAgendamento status;

    public Consulta(Beneficiario beneficiario,
                    Dentista dentista,
                    LocalDateTime dataHora,
                    Prioridade prioridade,
                    StatusAgendamento status) {

        if (beneficiario == null) {
            throw new IllegalArgumentException("Beneficiário é obrigatório");
        }

        if (dentista == null) {
            throw new IllegalArgumentException("Dentista é obrigatório");
        }

        if (dataHora == null || dataHora.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data/hora inválida");
        }

        this.beneficiario = beneficiario;
        this.dentista = dentista;
        this.dataHora = dataHora;
        this.prioridade = (prioridade != null) ? prioridade : Prioridade.MEDIA;
        this.status = (status != null) ? status : StatusAgendamento.AGENDADO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void cancelar() {
        this.status = StatusAgendamento.CANCELADO;
    }

    public void finalizar() {
        this.status = StatusAgendamento.REALIZADO;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", beneficiario=" + beneficiario.getNome() +
                ", dentista=" + dentista.getNome() +
                ", dataHora=" + dataHora +
                ", prioridade=" + prioridade +
                ", status=" + status +
                '}';
    }
}