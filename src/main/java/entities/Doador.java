package entities;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Doador extends Pessoa {
    private LocalDate data;
    private BigDecimal valorDoado;
    private Contato contato;

    public Doador(Long id, String nome, String endereco, String email,
                  Contato contato, LocalDate data, BigDecimal valorDoado) {

        super(id, nome, endereco, email, contato);
        this.data = data;
        this.valorDoado = valorDoado;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorDoado() {
        return valorDoado;
    }

    public void setValorDoado(BigDecimal valorDoado) {
        this.valorDoado = valorDoado;
    }

    @Override
    public String toString() {
        return "Doador{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", data=" + data +
                ", valorDoado=" + valorDoado +
                '}';
    }
}