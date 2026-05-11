package entities;

public class Dentista extends Pessoa {

    private String cro;

    public Dentista(Long id, String nome, String endereco, String email, String telefone, String cro) {
        super(id, nome, endereco, email, new Contato(telefone, "", ""));

        if (cro == null || cro.isBlank()) {
            throw new IllegalArgumentException("CRO é obrigatório");
        }
        this.cro = cro;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    @Override
    public String toString() {
        return "Dentista{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", cro='" + cro + '\'' +
                '}';
    }
}