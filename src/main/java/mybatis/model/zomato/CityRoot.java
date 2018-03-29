package mybatis.model.zomato;

public class CityRoot {

    //maps the JSON file from Zomato API contains location_suggestions array. location_suggestions class needs to be created.
    location_suggestions [] location_suggestions;

    //getters and setters
    public mybatis.model.zomato.location_suggestions[] getLocation_suggestions() {
        return location_suggestions;
    }

    public void setLocation_suggestions(mybatis.model.zomato.location_suggestions[] location_suggestions) {
        this.location_suggestions = location_suggestions;
    }
}
