package ut.seniordesign.cowdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<>();
    private Context context;

    public CustomAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_button_item, null);
        }

        Button templateBtn = (Button)view.findViewById(R.id.template_btn);
        templateBtn.setText(list.get(position));
        templateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://cowdb.kazeinc.com/redirect";
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "Posted: location=" + list.get(position), Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Failed to post", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("location", list.get(position));
                        return MyData;
                    }
                };

                MySingleton.getInstance(context).addToRequestQueue(stringRequest);
            }
        });

        return view;
    }
}
