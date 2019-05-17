package geekbrains.ru.weatherapp;

import android.graphics.drawable.Drawable;

import java.util.HashMap;

public class DataClass extends HashMap<String, String> {
    public Drawable drawableWheather;
    public String cityName;
    boolean isChecked;

    public String getCityName() {
        return cityName;
    }

    public Drawable getDrawableWheather() {
        return drawableWheather;
    }

    DataClass(Drawable drawableWeather, String cityName, boolean isChecked) {
        this.drawableWheather = drawableWeather;
        this.cityName = cityName;
        this.isChecked = isChecked;
    }
}
