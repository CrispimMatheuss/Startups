package model;

import java.time.LocalDate;

public class Startups extends Entity{
    private String nomeStartup;
    private String descStartup;
    private LocalDate dataInicio;
    private Integer codigoCidade;
    private String enderecoStartup;
    private String descSolucoes;

    public String getDescSolucoes() {
        return descSolucoes;
    }

    public void setDescSolucoes(String descSolucoes) {
        this.descSolucoes = descSolucoes;
    }

    public Startups() {

    }

    public String getNomeStartup() {
        return nomeStartup;
    }

    public void setNomeStartup(String nomeStartup) {
        this.nomeStartup = nomeStartup;
    }

    public String getDescStartup() {
        return descStartup;
    }

    public void setDescStartup(String descStartup) {
        this.descStartup = descStartup;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getCodigoCidade() {
        return codigoCidade;
    }

    public void setCodigoCidade(Integer codigoCidade) {
        this.codigoCidade = codigoCidade;
    }

    public String getEnderecoStartup() {
        return enderecoStartup;
    }

    public void setEnderecoStartup(String enderecoStartup) {
        this.enderecoStartup = enderecoStartup;
    }

    @Override
    public String toString() {
        return "Startups{" +
                "nomeStartup='" + nomeStartup + '\'' +
                ", descStartup='" + descStartup + '\'' +
                ", dataInicio=" + dataInicio +
                ", codigoCidade=" + codigoCidade +
                ", enderecoStartup='" + enderecoStartup + '\'' +
                '}';
    }
}
