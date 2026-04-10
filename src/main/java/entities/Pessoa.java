package entities;

public class Pessoa {
    private Long id;
    private String nome;
    private String endereco;
    private String email;
    private Contato contato;
    private Boolean ativo;

    public Pessoa(Long id, String nome, String endereco, String email, Contato contato) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.contato = contato;
        this.ativo = true;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getEmail() { return email; }
    public Contato getContato() { return contato; }
    public Boolean getAtivo() { return ativo; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public void setEmail(String email) { this.email = email; }
    public void setContato(Contato contato) { this.contato = contato; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}