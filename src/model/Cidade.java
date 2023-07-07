package model;

public class Cidade extends Entity{

    private String nomeCidade;
    private Estados estados;

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Estados getEstados() {
        return estados;
    }

    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    @Override
    public String toString() {
        return  "Nome: '" + nomeCidade + '\'' +
                ", UF: " + estados +
                '}';
    }
}
