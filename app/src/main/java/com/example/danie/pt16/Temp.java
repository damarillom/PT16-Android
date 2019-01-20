package com.example.danie.pt16;

import java.text.Format;

public class Temp {

        private String data;
        private String tempe;
        private String imatge;
        private String humidity;
        private String press;
        private String formatTemperature;

    public Temp(String data, String tempe, String imatge, String humid, String press) {
        this.data = data;
        this.tempe = tempe;
        this.imatge = imatge;
        this.humidity=humid;
        this.press=press;
        this.formatTemperature = "ºC";

    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getData() {
        return data;
    }

    public String getTempe() {
        return tempe;
    }

    public String getImatge() {
        return imatge;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTempe(String tempe) {
        this.tempe = tempe;
    }

    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getFormatTemperature() {
        return formatTemperature;
    }

    public void setFormatTemperature(String formatTemperature) {
        this.formatTemperature = formatTemperature;
    }
}
