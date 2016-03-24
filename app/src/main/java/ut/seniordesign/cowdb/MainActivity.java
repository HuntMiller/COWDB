package ut.seniordesign.cowdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

        Random rand = new Random();
        generateButtons(rand.nextInt(10) + 1);

    }

    public void generateButtons(int amt){
        for(int i = 0; i < amt; i++){
            list.add("template" + i);
        }
    }

}
