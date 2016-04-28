package ut.seniordesign.cowdb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Context context;
    public ListView listView;
    public ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        String defaultURL = "http://172.31.247.168:5002";
        final EditText editText = (EditText) findViewById(R.id.edit_message);
        editText.setText(defaultURL);

        final Button loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                final String url = s;

                JsonObjectRequest jsObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, url + "/endpoints", null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            list = new ArrayList<>();
                            list.add(response.getString("main"));
                            list.add(response.getString("alternate0"));
                            list.add(response.getString("alternate1"));
                            list.add(response.getString("alternate2"));
                            list.add(response.getString("alternate3"));

                            CustomAdapter adapter = new CustomAdapter(url, list, context);
                            listView = (ListView) findViewById(R.id.listView);
                            listView.setAdapter(adapter);

                            editText.setVisibility(View.GONE);
                            loadButton.setVisibility(View.GONE);

                        } catch (Exception e) {
                            //catch an exception
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //do something on error
                    }
                });

                MySingleton.getInstance(context).addToRequestQueue(jsObjectRequest);
            }
        });
    }
}
