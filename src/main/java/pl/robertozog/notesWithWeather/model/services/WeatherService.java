package pl.robertozog.notesWithWeather.model.services;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.robertozog.notesWithWeather.model.dto.WeatherDto;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WeatherService {


    Gson gson;

    public WeatherDto loadWeather(String city, String appiKey){

        loadWeatherService();

        return convertJsonToWeatherDto(readWeabSite("https://api.openweathermap.org/data/2.5/weather?q="
                + city +
                "&appid=" + appiKey));

    }

    public void loadWeatherService(){
        gson = new Gson();
    }

    private WeatherDto convertJsonToWeatherDto(String json){
        return gson.fromJson(json,WeatherDto.class);
    }

    private String readWeabSite(String url){

        StringBuilder sb = new StringBuilder();
        HttpURLConnection http;
        try {
            http = (HttpURLConnection) new URL(url).openConnection();
            http.setRequestMethod("GET");
            InputStream inputStream = http.getInputStream();

            int data;
            while((data = inputStream.read()) != -1){
                sb.append((char)data);
            }
            inputStream.close();
        } catch (IOException e) {
            System.out.println("brak polaczenia z internetem");
            e.printStackTrace();

        }
        return sb.toString();
    }

}
