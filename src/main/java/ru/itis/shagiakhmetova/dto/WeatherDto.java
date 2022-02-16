package ru.itis.shagiakhmetova.dto;

import ru.itis.shagiakhmetova.model.Weather;

public class WeatherDto {
    private Integer id;
    private String email;
    private String temp;
    private String humidity;
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public WeatherDto(Integer id, String email, String temp, String humidity, String city) {
        this.id = id;
        this.email = email;
        this.temp = temp;
        this.humidity = humidity;
        this.city = city;
    }
    public static WeatherDto fromModel(Weather weather) {
        return new WeatherDto(weather.getId(), weather.getEmail(),
                weather.getTemp(), weather.getHumidity(), weather.getCity());
    }
}
