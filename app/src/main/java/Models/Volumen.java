package Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Volumen {
    String titulo;
    String fecha;
    String doi;
    String  urlPortada;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public Volumen(JSONObject a) throws JSONException {
        titulo =  a.getString("title").toString();

        fecha =  a.getString("date_published").toString() ;
        doi =  a.getString("doi").toString() ;
        urlPortada= a.getString("cover").toString() ;



    }

    public static ArrayList<Volumen> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Volumen> volumenes = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            volumenes.add(new Volumen(datos.getJSONObject(i)));
        }
        return volumenes;
    }
}
