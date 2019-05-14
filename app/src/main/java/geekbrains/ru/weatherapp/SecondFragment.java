package geekbrains.ru.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    public static final String PARCEL = "parcel";

    private int temperature;
    private TextView tvCityName;
    private TextView tvCityTemperature;
    private TextView tvCityPrecipitation;
    private TextView tvAtmospherePressure;
    private TextView tvSpeedWind;
    private ImageView ivCityPrecipitation;

    // фабричный метод, создает фрагмент и передает параметр
    public static SecondFragment create(Parsel parcel) {
        SecondFragment secondFragment = new SecondFragment();    // создание

        // передача параметра
        Bundle args = new Bundle();
        args.putSerializable(PARCEL, parcel);
        secondFragment.setArguments(args);
        return secondFragment;
    }

    // получить индекс из списка (фактически из параметра)
    public Parsel getParcel() {
        return (Parsel) getArguments().getSerializable(PARCEL);
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getCity();
    }

    private void getCity() {
        Parsel parsel = getParcel();

        tvCityName.setText(getResources().getString(R.string.colonAndSpace) + parsel.getText());

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
}
