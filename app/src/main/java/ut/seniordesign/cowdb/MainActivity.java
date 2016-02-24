package ut.seniordesign.cowdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        tv.setText("COWDB is revolutionary");
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.button:{
                tv.setText(randomStr());
                break;
            }
        }
    }

    protected String randomStr(){
        String[] arr = {"great", "wonderful", "perfect", "awesome", "admirable", "amazing", "astonishing", "brilliant", "cool", "enjoyable", "excellent", "fabulous", "fantastic", "magnificent"};
        Random rand = new Random();
        String str = "COWDB is " + arr[rand.nextInt(arr.length + 1)];
        return str;
    }
}
