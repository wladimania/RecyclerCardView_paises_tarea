package Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revista {

    private String titulo;
    private String portada;
    private String journal_id;

    public String getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(String journal_id) {
        this.journal_id = journal_id;
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public Revista(JSONObject a) throws JSONException {
        journal_id =  a.getString("journal_id").toString();
        titulo =  a.getString("name").toString();
        portada =  a.getString("portada").toString() ;

    }

    public static ArrayList<Revista> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Revista> Revistas = new ArrayList<>();

        for (int i = 0; i < datos.length(); i++) {
            Revistas.add(new Revista(datos.getJSONObject(i)));
        }
        return Revistas;
    }
}
