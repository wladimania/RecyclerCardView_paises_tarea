package uteq.solutions.recyclercardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

import Adapter.RevistaAdaptador;
import Adapter.UsuarioAdaptador;
import Models.Revista;
import Models.Usuario;

public class Actividad_Revista extends AppCompatActivity {

    RecyclerView revistarecicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_revista);
        revistarecicle = (RecyclerView) findViewById(R.id.revista_recicle);
        revistarecicle.setHasFixedSize(true);
        revistarecicle.setLayoutManager(new LinearLayoutManager(this));
        revistarecicle.setItemAnimator(new DefaultItemAnimator());

        // ...

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://revistas.uteq.edu.ec/ws/journals.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Revista> revistas = new ArrayList<Revista> ();

                        try {
                            JSONArray JSONlistaRevista=  new JSONArray(response);
                            revistas = Revista.JsonObjectsBuild(JSONlistaRevista);


                            int resId = R.anim.layout_animation_down_to_up;
                            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(),
                                    resId);
                            revistarecicle.setLayoutAnimation(animation);


                            RevistaAdaptador adapatorrevista = new RevistaAdaptador(getApplicationContext(), revistas);
                            revistarecicle.setAdapter(adapatorrevista);

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