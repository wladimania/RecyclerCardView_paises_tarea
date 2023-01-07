package uteq.solutions.recyclercardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

import java.util.ArrayList;

import Adapter.RevistaAdaptador;
import Adapter.VolumenAdaptador;
import Models.Revista;
import Models.Volumen;

public class actvidadVolumenes extends AppCompatActivity {
    RecyclerView recyclerVol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actvidad_volumenes);
        recyclerVol = (RecyclerView) findViewById(R.id.recyclerVolumenes);
        recyclerVol.setHasFixedSize(true);
        recyclerVol.setLayoutManager(new LinearLayoutManager(this));
        recyclerVol.setItemAnimator(new DefaultItemAnimator());

        Bundle bundle = this.getIntent().getExtras();
        String IDRevista = bundle.getString("id");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://revistas.uteq.edu.ec/ws/issues.php?j_id=" + IDRevista;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Volumen> volumenes = new ArrayList<Volumen> ();

                        try {
                            JSONArray JSONlistaVolumen=  new JSONArray(response);
                            volumenes = Volumen.JsonObjectsBuild(JSONlistaVolumen);


                            int resId = R.anim.layout_animation_down_to_up;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                                    resId);
                            recyclerVol.setLayoutAnimation(animation);


                            VolumenAdaptador adapatorVolumen = new VolumenAdaptador(getApplicationContext(), volumenes);
                            recyclerVol.setAdapter(adapatorVolumen);

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