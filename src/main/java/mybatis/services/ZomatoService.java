package mybatis.services;
import mybatis.mappers.zomato.ZomatoMapper;
import mybatis.model.zomato.CityOverview;
import mybatis.model.zomato.CityRoot;
import mybatis.model.zomato.CollectionsRoot;
import mybatis.model.zomato.ZomatoCollectionsOverview;
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
    public HttpHeaders sendApiInHeader() {

        HttpHeaders header = new HttpHeaders();
        header.set("user-key", apiKey);
        return header;
    }


    //method that gets the cityID from ZomatoAPI
    public CityRoot getCityID(String query, boolean insert) {

        //sending the API Key in the header each time request is made
        HttpEntity<String> request = new HttpEntity<>(sendApiInHeader());

        //the url query
        String url = "https://developers.zomato.com/api/v2.1/cities?q=" + query;

        //using restTemplate to send the request using exchange method
        CityRoot response = restTemplate.exchange(url, HttpMethod.GET, request, CityRoot.class).getBody();


        //if insert is true, saveData will be called - saving data to database
        if (insert) {

                saveCityIdData(response);
        }
        return response;
    }


    //method that saves the query to the database
    public void saveCityIdData(CityRoot response) {



        for (int i = 0; i < response.getLocation_suggestions().length ; i++) {

            //declare a new object to insert to the database using CityOverview Class as a template
            CityOverview summary = new CityOverview();

            //setting the city_id variable from location_suggestions array
             summary.setCity_id(response.getLocation_suggestions()[i].getId());

             //setting the city_name from location_suggestions array
            summary.setCity_name(response.getLocation_suggestions()[i].getName());

            //setting the country_id from location_suggestions array
            summary.setCountry_id(response.getLocation_suggestions()[i].getCountry_id());

            //setting the country name
            summary.setCountry_name(response.getLocation_suggestions()[i].getCountry_name());

            //setting the state name
            summary.setState_name(response.getLocation_suggestions()[i].getState_name());

            //setting the state code
            summary.setState_code(response.getLocation_suggestions()[i].getState_code());

            //using the mapper call the insert method from the interface passing the CityOverview object
            zomatoMapper.insertCityID(summary);
        }

    }

    //create a method that search collections of restaurants
    public CollectionsRoot getCollections(String cityName, boolean insert) {

        // use the get city id method to get the ID and store it in a variable
        int cityID = getCityID(cityName, insert).getLocation_suggestions()[0].getId();

        //sending the API Key in the header each time request is made
        HttpEntity<String> request = new HttpEntity<>(sendApiInHeader());

        // pass the variable to the query to get collections
        String url = "https://developers.zomato.com/api/v2.1/collections?city_id=" + cityID;

        //using restTemplate to send the request using exchange method
        CollectionsRoot response = restTemplate.exchange(url, HttpMethod.GET, request, CollectionsRoot.class).getBody();

//        if (insert){
//            saveCollections(response,cityName,cityID);
//        }

        return response;
        // save the list to the database
    }

//    public void saveCollections (CollectionsRoot collection, String cityName, int cityID){
//
//        int collectionLength = collection.getCollections().length;
//        ZomatoCollectionsOverview [] overviews = new ZomatoCollectionsOverview[collectionLength];
//
//
//        for (int i = 0; i < overviews.length; i++) {
//
//
//        }

//        ZomatoCollectionsOverview overview = new ZomatoCollectionsOverview();
//        overview.setCity_id(cityID);
//        overview.setCity_name(cityName);
//
//        for (int i = 0; i <collection.getCollections().length - 1  ; i++) {
//            overview.setCollection_id(collection.getCollections()[i].getCollection().getCollection_id());
//            overview.setTitle(collection.getCollections()[i].getCollection().getTitle());
//            overview.setDescription(collection.getCollections()[i].getCollection().getTitle());
//            overview.setUrl(collection.getCollections()[i].getCollection().getUrl());
//        }

//        zomatoMapper.insertCollections(overview);
//    }
}

