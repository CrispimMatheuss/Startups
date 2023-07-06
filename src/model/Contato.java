package model;

public class Contato extends Entity{
    private String nome;
    private Integer idStartup;
    private Integer idTipoContato;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdStartup() {
        return idStartup;
    }

    public Integer getIdTipoContato() {
        return idTipoContato;
    }

    public void setIdStartup(Integer idStartup) {
        this.idStartup = idStartup;
    }

    public void setIdTipoContato(Integer idTipoContato) {
        this.idTipoContato = idTipoContato;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "nome='" + nome + '\'' +
                ", idStartup=" + idStartup +
                ", idTipoContato=" + idTipoContato +
                '}';
    }
}
