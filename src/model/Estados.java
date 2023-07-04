package model;

public enum Estados {

    SANTA_CATARINA("SC"),
    RIO_GRANDE_DO_SUL("RS");
    private String UF;

    Estados(String sigla){
        this.UF = sigla;
    }

    public String getUF(){
        return UF;
    }

}
