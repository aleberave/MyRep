package geekbrains.ru.weatherapp;

import java.util.HashMap;

public class DataClass extends HashMap<String, String> {
    public String cityName;
    boolean isChecked;
    public int identifier;

    public DataClass(int identifier, String cityName, boolean isChecked) {
        this.identifier = identifier;
        this.cityName = cityName;
        this.isChecked = isChecked;
    }

    public String getCityName() {
        return cityName;
    }

    public int getIdentifier() {
        return identifier;
    }
}
