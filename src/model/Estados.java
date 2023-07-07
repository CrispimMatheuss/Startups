package model;

public enum Estados {

    PR("PR"),
    SP("SP"),
    RJ("RJ"),
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