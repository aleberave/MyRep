package geekbrains.ru.weatherapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


// use https://androstock.com/tutorials/create-a-weather-app-on-android-android-studio.html
// Project Created by Ferdousur Rahman Shajib
// www.androstock.com

public class SecondFragment2 extends Fragment {

    public static final String PARCEL = "parcel";

    TextView selectCity, cityField, detailsField, currentTemperatureField, HumidityField,
            PressureField, weatherIcon, UpdatedField;
    ProgressBar loader;
    Typeface weatherFont;
    String city = "";
    String OPEN_WEATHER_MAP_API = "cbfdb21fa1793c10b14b6b6d00fbef03";

    // фабричный метод, создает фрагмент и передает параметр
    public static SecondFragment2 create(Parsel parcel) {
        SecondFragment2 secondFragment2 = new SecondFragment2();    // создание
        // передача параметра
        Bundle args = new Bundle();
        args.putSerializable(PARCEL, parcel);
        secondFragment2.setArguments(args);
        return secondFragment2;
    }

    // получить индекс из списка (фактически из параметра)
    public Parsel getParcel() {
        assert getArguments() != null;
        return (Parsel) getArguments().getSerializable(PARCEL);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second_2, container, false);
        setRetainInstance(true);
        init(v);
        return v;
    }

    private void init(View view) {
        loader = view.findViewById(R.id.loader);
        selectCity = view.findViewById(R.id.selectCity);
        cityField = view.findViewById(R.id.city_field);
        UpdatedField = view.findViewById(R.id.updated_field);
        detailsField = view.findViewById(R.id.details_field);
        currentTemperatureField = view.findViewById(R.id.current_temperature_field);
        HumidityField = view.findViewById(R.id.humidity_field);
        PressureField = view.findViewById(R.id.pressure_field);
        weatherIcon = view.findViewById(R.id.weather_icon);
        weatherFont = Typeface.createFromAsset(Objects.requireNonNull(getActivity()).getAssets(),
                getResources().getString(R.string.fonts));
        weatherIcon.setTypeface(weatherFont);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Parsel parsel = getParcel();

        city = parsel.getTextCityName();
        taskLoadUp(city);

        selectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle(getResources().getString(R.string.sTitleChangeCity));
                final EditText input = new EditText(getActivity());
                input.setText(city);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton(getResources().getString(R.string.change),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                city = input.getText().toString();
                                taskLoadUp(city);
                            }
                        });
                alertDialog.setNegativeButton(getResources().getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    public void taskLoadUp(String query) {
        if (Function.isNetworkAvailable(Objects.requireNonNull(getContext()))) {
            DownloadWeather task = new DownloadWeather();
            task.execute(query);
        } else {
            Toast.makeText(getContext(), getResources().getString(R.string.noInternetConnection),
                    Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class DownloadWeather extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);

        }

        protected String doInBackground(String... args) {
            StringBuffer stb = new StringBuffer();
            stb.append(getResources().getString(R.string.targetURL1)).append(args[0])
                    .append(getResources().getString(R.string.targetURL2))
                    .append(OPEN_WEATHER_MAP_API);
            return Function.excuteGet(String.valueOf(stb));
        }

        @SuppressLint("DefaultLocale")
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                JSONObject details = json.getJSONArray(getResources().getString(R.string.weather))
                        .getJSONObject(0);
                JSONObject main = json.getJSONObject(getResources().getString(R.string.main));
                DateFormat df = DateFormat.getDateTimeInstance();

                StringBuffer stbCityField = new StringBuffer();
                stbCityField.append(json.getString(getResources().getString(R.string.name))
                        .toUpperCase(Locale.US))
                        .append(getResources().getString(R.string.commaAndSpace))
                        .append(json.getJSONObject(getResources()
                                .getString(R.string.sys)).getString(getResources()
                                .getString(R.string.country)));
                cityField.setText(stbCityField);

                detailsField.setText(details.getString(getResources().getString(R.string.description)).toUpperCase(Locale.US));

                StringBuffer stbCurrentTemperatureField = new StringBuffer();
                stbCurrentTemperatureField
                        .append(String.format(getResources().getString(R.string.number),
                                main.getDouble(getResources().getString(R.string.temp))))
                        .append(getResources().getString(R.string.sGradus));
                currentTemperatureField.setText(stbCurrentTemperatureField);

                StringBuffer stbHumidityField = new StringBuffer();
                stbHumidityField.append(getResources().getString(R.string.TitleHumidity))
                        .append(main.getString(getResources().getString(R.string.humidity)))
                        .append(getResources().getString(R.string.sProcent));
                HumidityField.setText(stbHumidityField);

                StringBuffer stbPressureField = new StringBuffer();
                stbPressureField.append(getResources().getString(R.string.TitlePressure))
                        .append(main.getString(getResources().getString(R.string.pressure)))
                        .append(getResources().getString(R.string.hPa));
                PressureField.setText(stbPressureField);

                UpdatedField.setText(df.format(new Date(json.getLong
                        (getResources().getString(R.string.dt)) * 1000)));

                weatherIcon.setText(Html.fromHtml(Function.setWeatherIcon(details
                                .getInt(getResources().getString(R.string.is)),
                        json.getJSONObject(getResources().getString(R.string.sys))
                                .getLong(getResources().getString(R.string.sunrise)) * 1000,
                        json.getJSONObject(getResources().getString(R.string.sys))
                                .getLong(getResources().getString(R.string.sunset)) * 1000)));

                loader.setVisibility(View.GONE);
            } catch (JSONException e) {
                Toast.makeText(getContext(), getResources().getString(R.string.errorCheckCity),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
