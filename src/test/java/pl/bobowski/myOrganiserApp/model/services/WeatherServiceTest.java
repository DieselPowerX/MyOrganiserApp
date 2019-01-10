package pl.bobowski.myOrganiserApp.model.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherServiceTest {

    @Value("${api.key}")
    String api;

    @Autowired
    WeatherService weatherService;


    @Test
    void ifLoadingWeather(){
        assertNotNull(weatherService.loadWeather("Krakow",api));
    }

    @Test
    void ifLoadingGsonInstance(){
        assertNotNull(weatherService.loadGsonInstance());
    }

}