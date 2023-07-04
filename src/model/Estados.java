package model;

public enum Estados {

    SC("SC"),
    RS("RS");
    private String UF;

    Estados(String sigla){
        this.UF = sigla;
    }

    public String getUF(){
        return UF;
    }

}