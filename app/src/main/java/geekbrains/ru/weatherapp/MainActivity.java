package geekbrains.ru.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
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

        enterCityName = findViewById(R.id.enterCityName);
        cbTemperature = findViewById(R.id.cbTemperature);
        cbWind = findViewById(R.id.cbWind);
        swAtmospherePressure = findViewById(R.id.swAtmospherePressure);
        btnOk = findViewById(R.id.btnOk);
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnOkClick();
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

    private void btnOkClick() {
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
                if (checkedBox) {
                    CHECK_BOX_TEMPERATURE = true;
                } else {
                    CHECK_BOX_TEMPERATURE = false;
                }
                break;
            case R.id.cbWind:
                if (checkedBox) {
                    CHECK_BOX_WIND = true;
                } else {
                    CHECK_BOX_WIND = false;
                }
                break;
        }
    }

    public void onClickSwitch(View view) {
        boolean checkedSwitch = ((Switch) view).isChecked();
        switch (view.getId()) {
            case R.id.swAtmospherePressure:
                if (checkedSwitch) {
                    SWITCH_ATMOSPHERE_PRESSURE = true;
                } else {
                    SWITCH_ATMOSPHERE_PRESSURE = false;
                }
                break;
        }
    }
}
