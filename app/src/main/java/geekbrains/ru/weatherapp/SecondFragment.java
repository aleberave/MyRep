package geekbrains.ru.weatherapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.SENSOR_SERVICE;

public class SecondFragment extends Fragment {

    public static final String PARCEL = "parcel";

    //    private int temperature;
    private TextView tvCityName;
    private TextView tvCityTemperature;
    private TextView tvCityPrecipitation;
    private TextView tvCityHumidity;
    private TextView tvAtmospherePressure;
    private TextView tvSpeedWind;
    private ImageView ivCityPrecipitation;

    String sCityTemperature;
    private CompoundView compoundViewT;
    private SensorManager sensorManager;
    private Sensor sensorTemperature;
    private Sensor sensorHumidity;
    private boolean isSensorTemperature;
    private boolean isSensorHumidity;

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
        CompoundView compoundViewH = view.findViewById(R.id.compoundViewH);
        compoundViewT = view.findViewById(R.id.compoundViewT);
        tvCityTemperature = compoundViewT.findViewById(R.id.tvCompoundView);
        tvCityHumidity = compoundViewH.findViewById(R.id.tvCompoundView);
        tvCityPrecipitation = view.findViewById(R.id.tvCityPrecipitation);
        tvAtmospherePressure = view.findViewById(R.id.tvAtmospherePressure);
        tvSpeedWind = view.findViewById(R.id.tvSpeedWind);
        ivCityPrecipitation = view.findViewById(R.id.ivCityPrecipitation);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getSensors();
        getCityInfo();
    }

    private void getCityInfo() {
        Parsel parsel = getParcel();

        if (parsel != null) {
            String drawableStringID = getTemperature();

            StringBuilder cityName = new StringBuilder();
            cityName.append(getResources().getString(R.string.sColonAndSpace))
                    .append(parsel.getTextCityName());
            tvCityName.setText(cityName);

//        StringBuilder textTemperature = new StringBuilder(getResources().getString(R.string.colonAndSpace) +
//                temperature + getResources().getString(R.string.space) +
//                getResources().getString(R.string.sDegreesCelsius));
//        tvCityTemperature.setText(textTemperature);

            StringBuilder textWind = new StringBuilder();
            textWind.append(getResources().getString(R.string.sColonAndSpace))
                    .append(getResources().getString(R.string.sExCBWind))
                    .append(getResources().getString(R.string.sMetersPerSecond));
            tvSpeedWind.setText(textWind);

            StringBuilder textAtmospherePressure = new StringBuilder();
            textAtmospherePressure.append(getResources().getString(R.string.sColonAndSpace))
                    .append(getResources().getString(R.string.sExSWAtmospherePressure))
                    .append(getResources().getString(R.string.sAtmospherePressureMeasure));
            tvAtmospherePressure.setText(textAtmospherePressure);

            getDataCityList(parsel, drawableStringID);
        } else {
            tvCityName.setText(getString(R.string.sNoData));
            tvCityTemperature.setText(getString(R.string.sNoData));
            tvCityHumidity.setText(getString(R.string.sNoData));
            tvCityPrecipitation.setText(getString(R.string.sNoData));
            tvAtmospherePressure.setText(getString(R.string.sNoData));
            tvSpeedWind.setText(getString(R.string.sNoData));
        }
    }

    private void getDataCityList(Parsel parsel, String drawableStringID) {
        StringBuilder textDataCityName = new StringBuilder();
        textDataCityName.append(parsel.getTextCityName()).append(getResources().getString(R.string.sSpace))
                .append(tvCityTemperature.getText()).append(getResources().getString(R.string.sSpace))
                .append(drawableStringID);

        dataCity = new DataClass(ivCityPrecipitation.getId(), textDataCityName, false);
        if (list == null) {
            list = new ArrayList<>(6);
            list.add(dataCity.getCityName());
        } else {
            list.add(dataCity.getCityName());
        }
    }

    private String getTemperature() {
        float random = (float) Math.random();
        String addressDrawable;
        StringBuilder textCityPrecipitation = new StringBuilder();
        if (random < 0.5f) {
//            temperature = (int) (((-1) * random) * 30);
            addressDrawable = getResources().getString(R.string.sDrawableNightSnow);
            int drawableResID = getResources().getIdentifier(addressDrawable, null, null);
            ivCityPrecipitation.setImageResource(drawableResID);
            textCityPrecipitation.append(getResources().getString(R.string.sColonAndSpace))
                    .append(getResources().getString(R.string.sWaitSnow));
            tvCityPrecipitation.setText(textCityPrecipitation);
        } else {
//            temperature = (int) (random * 30);
            addressDrawable = getResources().getString(R.string.sDrawableSunCloud);
            int drawableResID = getResources().getIdentifier(addressDrawable, null, null);
            ivCityPrecipitation.setImageResource(drawableResID);
            textCityPrecipitation.append(getResources().getString(R.string.sColonAndSpace))
                    .append(getResources().getString(R.string.sWithoutPrecipitation));
            tvCityPrecipitation.setText(textCityPrecipitation);
        }
        return addressDrawable;
    }

    private void getSensors() {
        sensorManager =
                (SensorManager) Objects.requireNonNull(getActivity()).getSystemService(SENSOR_SERVICE);
        sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        isSensorTemperature = true;
        sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        isSensorHumidity = true;
        sensorManager.registerListener(listenerTemperature,
                sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listenerHumidity,
                sensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    SensorEventListener listenerHumidity = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            showHumiditySensor(event);
        }
    };

    SensorEventListener listenerTemperature = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            showTemperatureSensor(event);
        }
    };

    private void showHumiditySensor(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getResources().getString(R.string.sColonAndSpace))
                .append(event.values[0]).append(getResources().getString(R.string.sProcent));
        tvCityHumidity.setText(stringBuilder);
        tvCityHumidity.setVisibility(View.VISIBLE);
        String sCityHumidity = (String) tvCityHumidity.getText();
    }

    private void showTemperatureSensor(SensorEvent event) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getResources().getString(R.string.sColonAndSpace)).
                append(event.values[0]).append(getResources()
                .getString(R.string.sDegreesCelsius));
        tvCityTemperature.setText(stringBuilder);
        tvCityTemperature.setVisibility(View.VISIBLE);
        sCityTemperature = (String) tvCityTemperature.getText();
        Toast.makeText(getContext(), "tvTemperature" + sCityTemperature + "Wow", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listenerTemperature, sensorTemperature);
        sensorManager.unregisterListener(listenerHumidity, sensorHumidity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sensorManager = null;
        sensorTemperature = null;
        sensorHumidity = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isSensorTemperature) {
            sensorManager.registerListener(listenerTemperature,
                    sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (isSensorHumidity) {
            sensorManager.registerListener(listenerHumidity,
                    sensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
