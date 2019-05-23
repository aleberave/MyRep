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

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    public static final String PARCEL = "parcel";

    private int temperature;
    private TextView tvCityName;
    private TextView tvCityTemperature;
    private TextView tvCityPrecipitation;
    private TextView tvAtmospherePressure;
    private TextView tvSpeedWind;
    private ImageView ivCityPrecipitation;

    DataClass dataCity;
    public static ArrayList<StringBuilder> list = new ArrayList<>(0);

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
        View v = inflater.inflate(R.layout.fragment_second, container, false);

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

        getCityInfo();
    }

    private void getCityInfo() {
        Parsel parsel = getParcel();

        String drawableStringID = getTemperature();

        StringBuilder cityName = new StringBuilder(getResources().getString(R.string.colonAndSpace)
                + parsel.getText());
        tvCityName.setText(cityName);

        StringBuilder textTemperature = new StringBuilder(getResources().getString(R.string.colonAndSpace) +
                temperature + getResources().getString(R.string.space) +
                getResources().getString(R.string.sDegreesCelsius));
        tvCityTemperature.setText(textTemperature);

        StringBuilder textWind = new StringBuilder(getResources().getString(R.string.colonAndSpace) +
                getResources().getString(R.string.exCBWind) +
                getResources().getString(R.string.sMetersPerSecond));
        tvSpeedWind.setText(textWind);

        StringBuilder textAtmospherePressure = new StringBuilder(getResources().getString(R.string.colonAndSpace) +
                getResources().getString(R.string.exSWAtmospherePressure) +
                getResources().getString(R.string.sAtmospherePressureMeasure));
        tvAtmospherePressure.setText(textAtmospherePressure);

        getDataCityList(parsel, drawableStringID);
    }

    private void getDataCityList(Parsel parsel, String drawableStringID) {
        StringBuilder textDataCityName = new StringBuilder(parsel.getText()
                + getResources().getString(R.string.space) + tvCityTemperature.getText()
                + getResources().getString(R.string.space) + drawableStringID);

        dataCity = new DataClass(ivCityPrecipitation.getId(), textDataCityName, false);
        if (list == null) {
            list = new ArrayList<StringBuilder>(6);
            list.add(dataCity.cityName);
        } else {
            list.add(dataCity.cityName);
        }
    }

    private String getTemperature() {
        float random = (float) Math.random();
        String addressDrawable;
        StringBuilder textCityPrecipitation;
        if (random < 0.5f) {
            temperature = (int) (((-1) * random) * 30);
            addressDrawable = getResources().getString(R.string.sDrawableNightSnow);
            int drawableResID = getResources().getIdentifier(addressDrawable, null, null);
            ivCityPrecipitation.setImageResource(drawableResID);
            textCityPrecipitation = new StringBuilder(getResources().getString(R.string.colonAndSpace)
                    + getResources().getString(R.string.sWaitSnow));
            tvCityPrecipitation.setText(textCityPrecipitation);
        } else {
            temperature = (int) (random * 30);
            addressDrawable = getResources().getString(R.string.sDrawableSunCloud);
            int drawableResID = getResources().getIdentifier(addressDrawable, null, null);
            ivCityPrecipitation.setImageResource(drawableResID);
            textCityPrecipitation = new StringBuilder(getResources().getString(R.string.colonAndSpace)
                    + getResources().getString(R.string.sWithoutPrecipitation));
            tvCityPrecipitation.setText(textCityPrecipitation);
        }
        return addressDrawable;
    }
}
