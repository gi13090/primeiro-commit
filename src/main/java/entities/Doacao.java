package entities;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Doacao {
    private BigDecimal valorDoacao;
    private Doador doador;
    public LocalDate dataDoacao;



    public Doacao(BigDecimal valorDoacao, Doador doador,  LocalDate dataDoacao) {
        this.valorDoacao = valorDoacao;
        this.doador = doador;
        this.dataDoacao = dataDoacao;
    }

    public BigDecimal getValorDoacao() {
        return valorDoacao;
    }

    public void setValorDoacao(BigDecimal valorDoacao) {
        this.valorDoacao = valorDoacao;
    }
}
