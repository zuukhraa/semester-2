package ru.itis.shagiakhmetova.model;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    private String temp;
    private String humidity;
    private String city;

    public Weather() {
    }

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
    public Weather(String email,String temp, String humidity, String city) {
        this.email = email;
        this.temp = temp;
        this.humidity = humidity;
        this.city = city;
    }
}
