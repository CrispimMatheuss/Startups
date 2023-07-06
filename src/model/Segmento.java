package model;

public class Segmento extends Entity {
    private String nome;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Segmento{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
