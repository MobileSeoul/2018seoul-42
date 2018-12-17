package com.selecto.banana2;

import java.util.ArrayList;

public class Continent {
    private String name;
    private String tip;
    private ArrayList<Country> countryList = new ArrayList<Country>();

    public Continent(String name, String tip, ArrayList<Country> countryList) {
        super();
        this.name = name;
        this.tip = tip;
        this.countryList = countryList;
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
    public void setTip(String name) {
        this.tip = tip;
    }
    public ArrayList<Country> getCountryList() {
        return countryList;
    }
    public void setCountryList(ArrayList<Country> countryList) {
        this.countryList = countryList;
    };
}
