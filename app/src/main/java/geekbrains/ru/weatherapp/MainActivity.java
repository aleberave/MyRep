package geekbrains.ru.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TEXT = "text";
    public static final String BOOL_CBT = "bool_cbt";
    public static final String BOOL_CBW = "bool_cbw";
    public static final String BOOL_SAP = "bool_sap";

    static final String CITY_NAME = "CITY_NAME";
    private EditText enterCityName;
    private CheckBox cbTemperature;
    private CheckBox cbWind;
    private Switch swAtmospherePressure;
    private Button btnOk;

    public static boolean SWITCH_ATMOSPHERE_PRESSURE = false;
    public static boolean CHECK_BOX_TEMPERATURE = false;
    public static boolean CHECK_BOX_WIND = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        enterCityName = findViewById(R.id.enterCityName);
        cbTemperature = findViewById(R.id.cbTemperature);
        cbWind = findViewById(R.id.cbWind);
        swAtmospherePressure = findViewById(R.id.swAtmospherePressure);
        btnOk = findViewById(R.id.btnOk);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        enterCityName.setText("");
        CHECK_BOX_TEMPERATURE = false;
        cbTemperature.setChecked(false);
        CHECK_BOX_WIND = false;
        cbWind.setChecked(false);
        SWITCH_ATMOSPHERE_PRESSURE = false;
        swAtmospherePressure.setChecked(false);
    }

    public void btnOkClick(View view) {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterCityName.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), R.string.sEnterCityName, Toast.LENGTH_LONG).show();
                } else {
                    Parsel parsel = new Parsel();
                    parsel.text = enterCityName.getText().toString();

                    parsel.checkBoxTemperature = MainActivity.CHECK_BOX_TEMPERATURE;
                    parsel.checkBoxWind = MainActivity.CHECK_BOX_WIND;
                    parsel.switchAtmospherePressure = MainActivity.SWITCH_ATMOSPHERE_PRESSURE;

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra(CITY_NAME, parsel);
                    startActivity(intent);
                }
            }
        });
    }

    public void onClickCheckBox(View view) {
        boolean checkedBox = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.cbTemperature:
                CHECK_BOX_TEMPERATURE = checkedBox;
                break;
            case R.id.cbWind:
                CHECK_BOX_WIND = checkedBox;
                break;
        }
    }

    public void onClickSwitch(View view) {
        boolean checkedSwitch = ((Switch) view).isChecked();
        switch (view.getId()) {
            case R.id.swAtmospherePressure:
                SWITCH_ATMOSPHERE_PRESSURE = checkedSwitch;
                break;
        }
    }
}
