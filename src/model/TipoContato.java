package model;

public class TipoContato {
    private Integer id;
    private String nome;

    public TipoContato(Integer id, String nome) {
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
        return "TipoContato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
