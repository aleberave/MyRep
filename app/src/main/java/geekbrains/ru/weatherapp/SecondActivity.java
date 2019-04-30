package geekbrains.ru.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private int temperature;
    private TextView tvCityName;
    private TextView tvCityTemperature;
    private TextView tvCityPrecipitation;
    private TextView tvAtmospherePressure;
    private TextView tvSpeedWind;
    private ImageView ivCityPrecipitation;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        tvCityName = findViewById(R.id.tvCityName);
        tvCityTemperature = findViewById(R.id.tvCityTemperature);
        tvCityPrecipitation = findViewById(R.id.tvCityPrecipitation);
        tvAtmospherePressure = findViewById(R.id.tvAtmospherePressure);
        tvSpeedWind = findViewById(R.id.tvSpeedWind);
        ivCityPrecipitation = findViewById(R.id.ivCityPrecipitation);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getCity();
        btnBackClick();
    }

    private void getCity() {
        Parsel parsel = (Parsel) getIntent().getExtras().getSerializable(MainActivity.CITY_NAME);

        tvCityName.setText(": " + parsel.text);

        if (parsel.checkBoxTemperature) {
            getTemperature();
            tvCityTemperature.setText(": " + temperature + " " + getResources().getString(R.string.sDegreesCelsius));
        }
        if (parsel.checkBoxWind) {
            tvSpeedWind.setText(": 2 " + getResources().getString(R.string.sMetersPerSecond));
        }
        if (parsel.switchAtmospherePressure) {
            tvAtmospherePressure.setText(": 751.3 " + getResources().getString(R.string.sAtmospherePressureMeasure));
        }
    }

    private void getTemperature() {
        float random = (float) Math.random();
        if (random < 0.5f) {
            temperature = (int) (((-1) * random) * 30);
            ivCityPrecipitation.setImageResource(R.drawable.night_snow);
            tvCityPrecipitation.setText(": " + getResources().getString(R.string.sWaitSnow));
        } else {
            temperature = (int) (random * 30);
            ivCityPrecipitation.setImageResource(R.drawable.sun_cloud);
            tvCityPrecipitation.setText(": " + getResources().getString(R.string.sWithoutPrecipitation));
        }
    }

    private void btnBackClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parsel parsel = new Parsel();
                parsel.text = "";
                parsel.checkBoxTemperature = false;
                parsel.checkBoxWind = false;
                parsel.switchAtmospherePressure = false;
                Intent intentResult = new Intent();
                intentResult.putExtra(MainActivity.TEXT, parsel.text);
                intentResult.putExtra(MainActivity.BOOL_CBT, parsel.checkBoxTemperature);
                intentResult.putExtra(MainActivity.BOOL_CBW, parsel.checkBoxWind);
                intentResult.putExtra(MainActivity.BOOL_SAP, parsel.switchAtmospherePressure);
                setResult(Activity.RESULT_OK, intentResult);
                finish();
            }
        });
    }
}
