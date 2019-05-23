package geekbrains.ru.weatherapp;

public class DataClass {
    public StringBuilder cityName;
    public int identifier;
    boolean isChecked;

    public DataClass(int identifier, StringBuilder cityName, boolean isChecked) {
        this.identifier = identifier;
        this.cityName = cityName;
        this.isChecked = isChecked;
    }

    public StringBuilder getCityName() {
        return cityName;
    }

    public int getIdentifier() {
        return identifier;
    }
}
