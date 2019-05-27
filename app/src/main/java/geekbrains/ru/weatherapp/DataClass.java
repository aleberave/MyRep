package geekbrains.ru.weatherapp;

class DataClass {
    private StringBuilder cityName;
    private int identifier;
    private boolean isChecked;

    DataClass(int identifier, StringBuilder cityName, boolean isChecked) {
        this.identifier = identifier;
        this.cityName = cityName;
        this.isChecked = isChecked;
    }

    StringBuilder getCityName() {
        return cityName;
    }

    int getIdentifier() {
        return identifier;
    }
}
