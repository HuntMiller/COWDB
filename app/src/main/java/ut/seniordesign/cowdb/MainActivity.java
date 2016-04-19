package ut.seniordesign.cowdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import java.util.ArrayList;

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

        String url = "http://cowdb.kazeinc.com/endpoints";

        JsonObjectRequest jsObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try{
                    list.add(response.getString("main"));
                    list.add(response.getString("alternate0"));
                    list.add(response.getString("alternate1"));
                    list.add(response.getString("alternate2"));
                    list.add(response.getString("alternate3"));

                }catch(Exception e){
                    //catch an exception
                };
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //do something on error
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsObjectRequest);
    }
}
