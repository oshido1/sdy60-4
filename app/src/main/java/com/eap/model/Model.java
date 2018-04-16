package com.eap.model;

/**
 *
 */
public class Model {

	public static final String COLUMN_NAME = "NAME";
	public static final String COLUMN_MITROO = "MITROO";
	public static final String COLUMN_TMIMA = "TMIMA";
	public static final String COLUMN_EPIDOSI = "EPIDOSI";
	public static final String COLUMN_KAT_METAKINISIS = "KAT_METAKINISIS";
	public static final String COLUMN_FOREAS = "FOREAS";
	public static final String COLUMN_FOREAS_YPODOXIS = "FOREAS_YPODOXIS";
	public static final String COLUMN_COUNTRY_LAT = "COUNTRY_LAT";
	public static final String COLUMN_COUNTRY_LNG = "COUNTRY_LNG";
	public static final String COLUMN_METAKINISH = "METAKINISH";
	public static final String COLUMN_COUNTRY = "COUNTRY";

	private String name;
	private String mitroo;
	private String tmima;
	private String epidosi;
	private String kat_metakinisis;
	private String foreas;
	private String foreas_ipodoxis;
	private String country_lat;
	private String country_lng;
	private String metakinisi;
	private String country;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMitroo() {
		return mitroo;
	}

	public void setMitroo(String mitroo) {
		this.mitroo = mitroo;
	}

	public String getTmima() {
		return tmima;
	}

	public void setTmima(String tmima) {
		this.tmima = tmima;
	}

	public String getEpidosi() {
		return epidosi;
	}

	public void setEpidosi(String epidosi) {
		this.epidosi = epidosi;
	}

	public String getKat_metakinisis() {
		return kat_metakinisis;
	}

	public void setKat_metakinisis(String kat_metakinisis) {
		this.kat_metakinisis = kat_metakinisis;
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

	public String getCountry_lat() {
		return country_lat;
	}

	public void setCountry_lat(String country_lat) {
		this.country_lat = country_lat;
	}

	public String getCountry_lng() {
		return country_lng;
	}

	public void setCountry_lng(String country_lng) {
		this.country_lng = country_lng;
	}

	public String getMetakinisi() {
		return metakinisi;
	}

	public void setMetakinisi(String metakinisi) {
		this.metakinisi = metakinisi;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Model(String name, String mitroo, String tmima, String epidosi, String kat_metakinisis, String foreas, String foreas_ipodoxis, String country_lat, String country, String country_lng, String metakinisi) {
		this.name = name;
		this.mitroo = mitroo;
		this.tmima = tmima;
		this.epidosi = epidosi;
		this.kat_metakinisis = kat_metakinisis;
		this.foreas = foreas;
		this.foreas_ipodoxis = foreas_ipodoxis;
		this.country_lat = country_lat;
		this.country_lng = country_lng;
		this.metakinisi = metakinisi;
		this.country = country;
	}

	public Model(){}


	@Override
	public String toString() {
		return "Model{" +
				"name='" + name + '\'' +
				", mitroo='" + mitroo + '\'' +
				", tmima='" + tmima + '\'' +
				", epidosi='" + epidosi + '\'' +
				", kat_metakinisis='" + kat_metakinisis + '\'' +
				", foreas='" + foreas + '\'' +
				", foreas_ipodoxis='" + foreas_ipodoxis + '\'' +
				", country_lat='" + country_lat + '\'' +
				", country_lng='" + country_lng + '\'' +
				", metakinisi='" + metakinisi + '\'' +
				", country='" + country + '\'' +
				'}';
	}
}
