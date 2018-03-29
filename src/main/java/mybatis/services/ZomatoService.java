package mybatis.services;
import mybatis.mappers.zomato.ZomatoMapper;
import mybatis.model.zomato.CityOverview;
import mybatis.model.zomato.CityRoot;
import mybatis.model.zomato.CollectionsRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ZomatoService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ZomatoMapper zomatoMapper;

    //API key
    @Value("${zomato.apiKey}")
    String apiKey;

    //a method that sends the API Key in the header
    public HttpHeaders sendApiInHeader(){

        HttpHeaders header = new HttpHeaders();
        header.set("user-key", apiKey);
        return header;
    }


    //method that gets the cityID from ZomatoAPI
    public CityRoot getCityID (String query, boolean insert){

        //sending the API Key in the header each time request is made
        HttpEntity<String> request = new HttpEntity<>(sendApiInHeader());

        //the url query
        String url = "https://developers.zomato.com/api/v2.1/cities?q="+query;

        //using restTemplate to send the request using exchange method
        CityRoot response = restTemplate.exchange(url, HttpMethod.GET, request, CityRoot.class).getBody();

        //if insert is true, saveData will be called - saving data to database
        if (insert){
            saveCityIdData(response);
        }
        return response;
    }



    //method that saves the query to the database
    public void saveCityIdData(CityRoot response){

        //declare a new object to insert to the database using CityOverview Class as a template
        CityOverview summary = new CityOverview();

        //setting the city_id variable from location_suggestions array
        summary.setCity_id(response.getLocation_suggestions()[0].getId());

        //setting the city_name from from location_suggestions array
        summary.setCity_name(response.getLocation_suggestions()[0].getName());

        //using the mapper call the insert method from the interface passing the CityOverview object
        zomatoMapper.insertCityID(summary);
    }

    //create a method that search collections of restaurants
    public CollectionsRoot getCollections (int cityID){

        // use the get city id method to get the ID and store it in a variable
//        boolean insert = false;
//        int cityID = getCityID(cityName,insert).getLocation_suggestions()[0].getId();
//        String cityID = Integer.toString(getCityID(cityName,insert).getLocation_suggestions()[0].getId());

        //sending the API Key in the header each time request is made
        HttpEntity<String> request = new HttpEntity<>(sendApiInHeader());

        // pass the variable to the query to get collections
        String url = "https://developers.zomato.com/api/v2.1/collections?city_id="+cityID;

        //using restTemplate to send the request using exchange method
        CollectionsRoot response = restTemplate.exchange(url, HttpMethod.GET, request, CollectionsRoot.class).getBody();

        return response;

        // save the list to the database
    }



}
