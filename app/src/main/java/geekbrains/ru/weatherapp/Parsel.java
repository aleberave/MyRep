package geekbrains.ru.weatherapp;

import java.io.Serializable;

class Parsel implements Serializable {

    private int cityIndex;
    private String textCityName;

    Parsel(int index, String textCityName) {
        this.cityIndex = index;
        this.textCityName = textCityName;
    }

    String getTextCityName() {
        return textCityName;
    }

    int getCityIndex() {
        return cityIndex;
    }
}
