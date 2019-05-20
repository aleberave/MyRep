package geekbrains.ru.weatherapp;

public class DataClass {
    public String cityName;
    public int identifier;
    boolean isChecked;

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
