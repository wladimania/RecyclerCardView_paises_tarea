package uteq.solutions.recyclercardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import Adapter.PaisAdaptador;
import Adapter.VolumenAdaptador;
import Models.Pais;
import Models.Volumen;

public class actividadPaises2 extends AppCompatActivity {

    RecyclerView recyclerPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_paises2);

        recyclerPais = (RecyclerView) findViewById(R.id.recyclerPaises);
        recyclerPais.setHasFixedSize(true);
        recyclerPais.setLayoutManager(new LinearLayoutManager(this));
        recyclerPais.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = this.getIntent().getExtras();
//        String IDRevista = bundle.getString("id");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.geognos.com/api/en/countries/info/all.json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Pais> paises = new ArrayList<Pais> ();

                        try {
                            JSONObject JSONOBJECTpais = new JSONObject(response);
                            JSONObject JSONlistaPais=  JSONOBJECTpais.getJSONObject("Results");
                            Iterator<String> codigoPais =  JSONlistaPais.keys();

                            while (codigoPais.hasNext()) {
                                String aux;
                                aux=codigoPais.next();
                                JSONObject paisVaule = JSONlistaPais.getJSONObject(aux);
                               // System.out.println(paisVaule);
                                 Pais ps= new Pais(paisVaule);
                                paises.add(ps);
                               // System.out.println(ps);
                                Log.d("etiqueta",paisVaule.toString());
                                int resId = R.anim.layout_animation_down_to_up;
                                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                                        resId);
                                recyclerPais.setLayoutAnimation(animation);
                                PaisAdaptador adapatorPais = new PaisAdaptador(getApplicationContext(), paises);
                                recyclerPais.setAdapter(adapatorPais);
                            }
                        }
                        catch (JSONException e)
                        {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),  error.getMessage(), Toast.LENGTH_LONG).show();
                //TextView txtvolley = findViewById(R.id.txtlista);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}