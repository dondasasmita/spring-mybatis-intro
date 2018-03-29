package mybatis.services;

import mybatis.mappers.weather.WeatherMapper;
import mybatis.model.weather.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.apiKey}")
    String apiKey;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WeatherMapper weatherMapper;

    //method to get the weather by city
    public WeatherRoot currentWeatherByCity(String query, boolean persist) {
        String fQuery = "http://api.openweathermap.org/data/2.5/weather?q="+query+"&APPID="+apiKey;
        WeatherRoot response = restTemplate.getForObject(fQuery, WeatherRoot.class);
        if(persist) {
            //method to save data
            saveWeatherData(response);
        }

        return response;
    }


    //create method to save the data that accepts WeatherRoot
    public void saveWeatherData(WeatherRoot response) {

        WeatherOverview summary = new WeatherOverview();

        summary.setDate(summary.getDate());
        summary.setCity(response.getName());
        summary.setMain(response.getWeather()[0].getMain());
        summary.setDescription(response.getWeather()[0].getDescription());

        weatherMapper.insertWeather(summary);
    }

    //delete method
    public Weather deleteID(int id){
        weatherMapper.deleteID(id);
        return weatherMapper.getByID(id);
    }


//    public TwoCities searchTwoCities(String city1, String city2) {
//        CurrentCityRoot responseOne = currentWeatherByCity(city1);
//        CurrentCityRoot responseTwo = currentWeatherByCity(city2);
//
//        TwoCities obj = new TwoCities();
//        obj.setSearchCityOne(responseOne.toString());
//        obj.setSearchCityTwo(responseTwo.toString());
//        return obj;
//
//    }

}
