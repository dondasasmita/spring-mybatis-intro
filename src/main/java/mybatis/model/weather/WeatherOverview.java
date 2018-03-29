package mybatis.model.weather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherOverview {

    String date;
    String city;
    String main;
    String description;

    public String getDate() {
        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat();
        return dateFormat.format(today);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
