package entities;

public class Beneficiario extends Pessoa {
    private String cpf;

    public Beneficiario() {

    }
    public Beneficiario(Long id, String nome, String cpf, String telefone, String email, String endereco) {
        super(id, nome, endereco, email, new Contato(telefone, "", ""));

        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }

        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Beneficiario{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                '}';
    }
}