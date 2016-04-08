package ut.seniordesign.cowdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        CustomAdapter adapter = new CustomAdapter(list, this);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        //make the request
        final TextView textView = (TextView) findViewById(R.id.textView);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://cowdb.kazeinc.com/endpoints";

        String testing = "";
        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                //do something with the response
                //textView in place solely to see that the request went through
                textView.setText(response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //do something on error
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsObjectRequest);

        Random rand = new Random();
        generateButtons(rand.nextInt(10) + 1);

    }

    public void generateButtons(int amt){
        for(int i = 0; i < amt; i++){
            list.add("Button " + i);
        }
    }

}
