package com.selecto.banana2;

public class Shoplistitem {
    private String NM;
    private String ADDR_OLD;
    private String ADDR;
    private String TEL;

    public Shoplistitem(String NM, String ADDR_OLD, String ADDR, String TEL)
    {
        this.NM = NM;
        this.ADDR_OLD = ADDR_OLD;
        this.ADDR = ADDR;
        this.TEL = TEL;
    }

    public String getNM(){return NM;}
    public String getADDR_OLD(){return ADDR_OLD;}
    public String getADDR(){return ADDR;}
    public String getTEL(){return TEL;}
}
