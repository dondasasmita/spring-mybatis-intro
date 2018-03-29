package mybatis.model.weather;

import java.util.Arrays;

public class WeatherRoot {

    Weather[] weather;
    String name;


    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
