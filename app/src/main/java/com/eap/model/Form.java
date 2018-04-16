package com.eap.model;

/**
 * Created by skikos on 29/3/2018.
 */

public class Form {

    private String name;
    private int mitroo;
    private String tmima;
    private double epidosi;
    private String kat_metakinisis;
    private String foreas;
    private String foreas_ipodoxis;
    private Double country_lat;
    private Double country_lng;
    private String metakinisi;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMitroo() {
        return mitroo;
    }

    public void setMitroo(int mitroo) {
        this.mitroo = mitroo;
    }

    public String getTmima() {
        return tmima;
    }

    public void setTmima(String tmima) {
        this.tmima = tmima;
    }

    public double getEpidosi() {
        return epidosi;
    }

    public void setEpidosi(double epidosi) {
        this.epidosi = epidosi;
    }

    public String getForeas() {
        return foreas;
    }

    public void setForeas(String foreas) {
        this.foreas = foreas;
    }

    public String getForeas_ipodoxis() {
        return foreas_ipodoxis;
    }

    public void setForeas_ipodoxis(String foreas_ipodoxis) {
        this.foreas_ipodoxis = foreas_ipodoxis;
    }

    public String getMetakinisi() {
        return metakinisi;
    }

    public void setMetakinisi(String metakinisi) {
        this.metakinisi = metakinisi;
    }

    public Double getCountry_lat() {
        return country_lat;
    }

    public void setCountry_lat(Double country_lat) {
        this.country_lat = country_lat;
    }

    public Double getCountry_lng() {
        return country_lng;
    }

    public void setCountry_lng(Double country_lng) {
        this.country_lng = country_lng;
    }

    public String getKat_metakinisis() {
        return kat_metakinisis;
    }

    public void setKat_metakinisis(String kat_metakinisis) {
        this.kat_metakinisis = kat_metakinisis;
    }

    @Override
    public String toString() {
        return "Form{" +
                "name='" + name + '\'' +
                ", mitroo=" + mitroo +
                ", tmima='" + tmima + '\'' +
                ", epidosi=" + epidosi +
                ", kat_metakinisis='" + kat_metakinisis + '\'' +
                ", foreas='" + foreas + '\'' +
                ", foreas_ipodoxis='" + foreas_ipodoxis + '\'' +
                ", country_lat=" + country_lat +
                ", country_lng=" + country_lng +
                ", metakinisi='" + metakinisi + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
