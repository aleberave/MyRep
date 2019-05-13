package geekbrains.ru.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    public static final String TAG = SecondFragment.class.getSimpleName();

    private int temperature;
    private TextView tvCityName;
    private TextView tvCityTemperature;
    private TextView tvCityPrecipitation;
    private TextView tvAtmospherePressure;
    private TextView tvSpeedWind;
    private ImageView ivCityPrecipitation;
    private Button btnBack;

    public static SecondFragment init(Bundle bundle) {
        SecondFragment secondFragment = new SecondFragment();
        secondFragment.setArguments(bundle);
        return secondFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second_activity, container, false);

        setRetainInstance(true);

        init(v);

        return v;
    }

    private void init(View view) {
        tvCityName = view.findViewById(R.id.tvCityName);
        tvCityTemperature = view.findViewById(R.id.tvCityTemperature);
        tvCityPrecipitation = view.findViewById(R.id.tvCityPrecipitation);
        tvAtmospherePressure = view.findViewById(R.id.tvAtmospherePressure);
        tvSpeedWind = view.findViewById(R.id.tvSpeedWind);
        ivCityPrecipitation = view.findViewById(R.id.ivCityPrecipitation);
        btnBack = view.findViewById(R.id.btnBack);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getCity();
    }

    private void getCity() {
        Parsel parsel = (Parsel) getActivity().getIntent().getExtras().getSerializable(MainFragment.CITY_NAME);

        tvCityName.setText(getResources().getString(R.string.colonAndSpace) + parsel.text);

        if (parsel.checkBoxTemperature) {
            getTemperature();
            tvCityTemperature.setText(getResources().getString(R.string.colonAndSpace) +
                    temperature + getResources().getString(R.string.space) +
                    getResources().getString(R.string.sDegreesCelsius));
        }
        if (parsel.checkBoxWind) {
            tvSpeedWind.setText(getResources().getString(R.string.colonAndSpace) +
                    getResources().getString(R.string.exCBWind) +
                    getResources().getString(R.string.sMetersPerSecond));
        }
        if (parsel.checkBoxAtmospherePressure) {
            tvAtmospherePressure.setText(getResources().getString(R.string.colonAndSpace) +
                    getResources().getString(R.string.exSWAtmospherePressure) +
                    getResources().getString(R.string.sAtmospherePressureMeasure));
        }
        btnBack.setOnClickListener(btnBackClick);
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

    private final View.OnClickListener btnBackClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Parsel parsel = new Parsel();
            parsel.text = "";
            parsel.checkBoxTemperature = false;
            parsel.checkBoxWind = false;
            parsel.checkBoxAtmospherePressure = false;
            Intent intentResult = new Intent();
            intentResult.putExtra(MainFragment.TEXT, parsel.text);
            intentResult.putExtra(MainFragment.BOOL_CBT, parsel.checkBoxTemperature);
            intentResult.putExtra(MainFragment.BOOL_CBW, parsel.checkBoxWind);
            intentResult.putExtra(MainFragment.BOOL_SAP, parsel.checkBoxAtmospherePressure);
            ((Activity) v.getContext()).setResult(Activity.RESULT_OK, intentResult);
            ((Activity) v.getContext()).finish();
        }
    };

}
