package geekbrains.ru.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainFragment extends Fragment {

    public static final String TAG = MainFragment.class.getSimpleName();

    public static final String TEXT = "text";
    public static final String BOOL_CBT = "bool_cbt";
    public static final String BOOL_CBW = "bool_cbw";
    public static final String BOOL_SAP = "bool_sap";
    public static final int REQUEST_CODE = 10;

    static final String CITY_NAME = "CITY_NAME";
    private EditText enterCityName;
    private CheckBox cbTemperature;
    private CheckBox cbWind;
    private CheckBox cbAtmospherePressure;
    private Button btnOk;

    public static boolean CHECK_BOX_ATMOSPHERE_PRESSURE = false;
    public static boolean CHECK_BOX_TEMPERATURE = false;
    public static boolean CHECK_BOX_WIND = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_activity, container, false);

        setRetainInstance(true);

        initFrag(v);

        return v;
    }

    private void initFrag(View view) {
        enterCityName = view.findViewById(R.id.enterCityName);
        cbTemperature = view.findViewById(R.id.cbTemperature);
        cbWind = view.findViewById(R.id.cbWind);
        cbAtmospherePressure = view.findViewById(R.id.cbAtmospherePressure);
        btnOk = view.findViewById(R.id.btnOk);
    }

    public static MainFragment init(Bundle bundle) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViews(view);
    }

    private void setViews(@NonNull View v) {
        cbTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCheckBox(v);
            }
        });
        cbWind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCheckBox(v);
            }
        });
        cbAtmospherePressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCheckBox(v);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOkClick(v);
            }
        });
    }

    public void btnOkClick(View view) {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterCityName.getText().length() == 0) {
                    Toast.makeText(getActivity(), R.string.sEnterCityName, Toast.LENGTH_LONG).show();
                } else {
                    Parsel parsel = new Parsel();
                    parsel.text = enterCityName.getText().toString();

                    parsel.checkBoxTemperature = CHECK_BOX_TEMPERATURE;
                    parsel.checkBoxWind = CHECK_BOX_WIND;
                    parsel.checkBoxAtmospherePressure = CHECK_BOX_ATMOSPHERE_PRESSURE;

                    Intent intent = new Intent(getActivity(), SecondActivity.class);
                    intent.putExtra(CITY_NAME, parsel);
                    startActivityForResult(intent, MainFragment.REQUEST_CODE);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == getActivity().RESULT_OK) {
            enterCityName.setText(data.getStringExtra(TEXT));
            CHECK_BOX_TEMPERATURE = Boolean.parseBoolean(data.getStringExtra(BOOL_CBT));
            CHECK_BOX_WIND = Boolean.parseBoolean(data.getStringExtra(BOOL_CBW));
            CHECK_BOX_ATMOSPHERE_PRESSURE = Boolean.parseBoolean(data.getStringExtra(BOOL_SAP));
            cbTemperature.setChecked(CHECK_BOX_TEMPERATURE);
            cbWind.setChecked(CHECK_BOX_WIND);
            cbAtmospherePressure.setChecked(CHECK_BOX_ATMOSPHERE_PRESSURE);
        }
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
            case R.id.cbAtmospherePressure:
                CHECK_BOX_ATMOSPHERE_PRESSURE = checkedBox;
                break;
        }
    }

}
