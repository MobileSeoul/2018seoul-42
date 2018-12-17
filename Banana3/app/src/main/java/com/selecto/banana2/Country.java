package com.selecto.banana2;

public class Country {
    private String code = "";
    private String name = "";
    private String tip = "";

    public Country(String code, String name, String tip) {
        super();
        this.code = code;
        this.name = name;
        this.tip = tip;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTip() {
        return tip;
    }
    public void setTip(String tip) {
        this.tip = tip;
    }
}
