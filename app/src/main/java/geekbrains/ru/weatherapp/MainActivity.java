package geekbrains.ru.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int temperature;
    private EditText editText1;
    private TextView textView2;
    private TextView textView4;
    private TextView textView6;
    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        btnClick();
    }

    private void btnClick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Введите название города", Toast.LENGTH_LONG).show();
                    textView2.setText("");
                    textView4.setText("");
                    textView6.setText("");
                } else {
                    textView2.setText(String.valueOf(editText1.getText()));
                    editText1.setText("");
                    temperature();
                    textView4.setText(temperature + " " + getResources().getString(R.string.degrees_celsius));
                    textView6.setText("без осадков");
                }
            }
        });
    }

    private void temperature() {
        float random = (float) Math.random();
        if (random < 0.5f) {
            temperature = (int) (((-1) * random) * 30);
        } else {
            temperature = (int) (random * 30);
        }
    }
}
