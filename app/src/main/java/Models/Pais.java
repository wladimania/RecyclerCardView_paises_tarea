package Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pais {


    public String getUrlBandera() {
        return urlBandera;
    }

    public void setUrlBandera(String urlBandera) {
        this.urlBandera = urlBandera;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }
    private String urlBandera, titulo, capital, prefijo, codPais;

    public Pais(JSONObject a) throws JSONException {
        titulo =  a.getString("Name").toString();
        JSONObject countryCode = a.getJSONObject("CountryCodes");
        codPais = countryCode.getString("iso2");
        urlBandera =  "http://www.geognos.com/api/en/countries/flag/"+codPais+".png";
        JSONObject countryCapital = a.getJSONObject("Capital");
        capital =   countryCapital.getString("Name").toString() ;
        prefijo =  a.getString("TelPref").toString() ;
    }

    public static ArrayList<Pais> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Pais> paises = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            paises.add(new Pais(datos.getJSONObject(i)));
        }
        return paises;
    }
}
