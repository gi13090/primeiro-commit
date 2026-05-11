package entities;

public class Contato {
    private String telefone;
    private String prefixo;
    private String telefoneFixo;

    public Contato(String telefone, String prefixo, String telefoneFixo) {

        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone é obrigatório");
        }

        this.telefone = telefone;
        this.prefixo = prefixo;
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getPrefixo() {
        return prefixo;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("Telefone inválido");
        }
        this.telefone = telefone;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "telefone='" + telefone + '\'' +
                ", prefixo='" + prefixo + '\'' +
                ", telefoneFixo='" + telefoneFixo + '\'' +
                '}';
    }
}