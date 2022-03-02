package ru.itis.shagiakhmetova.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    private String temp;
    private String humidity;
    private String city;

    @OneToOne(mappedBy = "weather")
    private Appeal appeal;

    public Weather(String email, String temp, String humidity, String name) {
        this.email = email;
        this.temp = temp;
        this.humidity = humidity;
        this.city = name;
    }
}
