package enums;

public enum Prioridade {
    URGENCIA("Urgencia"),
    BAIXA("Baixa"),
    MEDIA("Media"),
    DATA("Data");

    private final String descricao;

    Prioridade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Prioridade{" + "descricao='" + descricao + '\'' + "} " + super.toString();
    }
}
