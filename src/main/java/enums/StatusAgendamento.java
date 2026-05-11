package enums;

public enum StatusAgendamento {
    AGENDADO ("Agendamento realizado"),
    CANCELADO ("Consulta cancelada"),
    PENDENTE ("Agendamento pendente"),
    REALIZADO ("Consulta realizada"),;
    private String descricao;

    StatusAgendamento(String descricao) {

        this.descricao = descricao;
    }
    public String getDescricao() {

        return descricao;
    }

    @Override
    public String toString() {
        return "StatusAtendimento{" + "descricao='" + descricao + '\'' + "} " + super.toString();
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
