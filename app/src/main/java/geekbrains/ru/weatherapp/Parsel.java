package geekbrains.ru.weatherapp;

import java.io.Serializable;

public class Parsel implements Serializable {

    private int cityIndex;
    public String text;

    public Parsel(int index, String text) {
        this.cityIndex = index;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getCityIndex() {
        return cityIndex;
    }

    public void setCityIndex(int position) {
        this.cityIndex = position;
    }
}
