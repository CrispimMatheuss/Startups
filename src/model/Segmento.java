package model;

public class Segmento {
    private Integer id;
    private String nome;

    public Segmento(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Segmento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
