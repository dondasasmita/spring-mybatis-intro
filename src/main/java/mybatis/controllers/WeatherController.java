package mybatis.controllers;

import mybatis.model.weather.Weather;
import mybatis.model.weather.WeatherRoot;
import mybatis.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("weather")

public class WeatherController {

    @Autowired
    WeatherService weatherService;

//    @RequestMapping ("/twoCities")
//    public TwoCities searchTwoCities(@RequestParam(value="query1", defaultValue = "1645528") String city1,
//                                     @RequestParam(value="query2", defaultValue = "1642911") String city2){
//        return weatherService.searchTwoCities(city1, city2);
//    }

    @RequestMapping("/currentWeather")
    public WeatherRoot currentWeatherByCity(@RequestParam(value="query", defaultValue = "Denpasar" ) String query,
                                                @RequestParam(value="persist", defaultValue = "false") boolean persist){
        return weatherService.currentWeatherByCity(query,persist);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/")
    public Weather deleteID(@RequestParam(value="id")int id){
        weatherService.deleteID(id);
        return weatherService.deleteID(id);
    }

}
