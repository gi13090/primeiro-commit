package entities;
import java.time.LocalDateTime;

public class Consulta {
    private Long id;
    private Beneficiario beneficiario;
    private Dentista dentista;
    private LocalDateTime dataHora;

    public Consulta(Beneficiario beneficiario, Dentista dentista, LocalDateTime dataHora) {
        this.beneficiario = beneficiario;
        this.dentista = dentista;
        this.dataHora = dataHora;
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

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", beneficiario=" + beneficiario.getNome() +
                ", dentista=" + dentista.getNome() +
                ", dataHora=" + dataHora +
                '}';
    }
}