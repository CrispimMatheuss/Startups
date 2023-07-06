package model;

import java.time.LocalDate;

public class Startups extends Entity{
    private String nomeStartup;
    private String descStartup;
    private LocalDate dataInicio;
    private String enderecoStartup;
    private String descSolucoes;
    private Integer codigoCidade;
    private Integer idSegmento;

    public String getDescSolucoes() {
        return descSolucoes;
    }

    public void setDescSolucoes(String descSolucoes) {
        this.descSolucoes = descSolucoes;
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

    public Integer getIdSegmento() {
        return idSegmento;
    }

    public void setIdSegmento(Integer idSegmento) {
        this.idSegmento = idSegmento;
    }

    @Override
    public String toString() {
        return "Startups{" +
                "nomeStartup='" + nomeStartup + '\'' +
                ", descStartup='" + descStartup + '\'' +
                ", dataInicio=" + dataInicio +
                ", enderecoStartup='" + enderecoStartup + '\'' +
                ", descSolucoes='" + descSolucoes + '\'' +
                ", codigoCidade=" + codigoCidade +
                ", idSegmento=" + idSegmento +
                '}';
    }
}
