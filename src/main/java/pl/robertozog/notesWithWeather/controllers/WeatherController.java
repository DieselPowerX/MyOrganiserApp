package pl.robertozog.notesWithWeather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.robertozog.notesWithWeather.model.services.WeatherService;

@Controller
public class WeatherController {

    @Value("${api.key}")
    String apiKey;

    final
    WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/user/weather")
    public String getWeather(Model model){


        System.out.println(weatherService.loadWeather("cairo",apiKey).getTempDto().getTemp());

        return "redirect: /user/dashboard";
    }
}
