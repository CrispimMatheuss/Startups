package model;

public class Contato {
    private Integer id;
    private String nome;


    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "contato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
