package mybatis.services;

//import mybatis.mappers.nyt.NYTMapper;
//import mybatis.model.nyt.external.NYTDocs;
//import mybatis.model.nyt.external.NYTRoot;
//import mybatis.model.nyt.challenges.ResponseComparison;
//import mybatis.model.nyt.internal.NYTOverview;
//import mybatis.model.threads.nyt.NYTOverviewThread;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.beans.factory.annotation.Value;

//@Service
public class NYTService {

//    @Autowired
//    RestTemplate restTemplate;
//
//    @Autowired
//    NYTMapper nytMapper;

//    @Value("${nyt.apiKey}")
//    String apiKey;
//
//    public NYTRoot searchNYT(String query, boolean persist) {
//        String fQuery = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q="+query+"&apiKey";
//        NYTRoot response = restTemplate.getForObject(
//                fQuery, NYTRoot.class);
//
//        System.out.println("********* got the results");
//
//        if (persist) {
//            System.out.println("*********** starting persistence thread");
//            NYTOverviewThread thread = new NYTOverviewThread(response);
//
//        }
//
//        return response;
//    }
//
//    public ResponseComparison compareNYTResults(String t1, String t2) {
//        NYTRoot responseOne = searchNYT(t1);
//        NYTRoot responseTwo = searchNYT(t2);
//        ResponseComparison obj = new ResponseComparison();
//        //set the first search term in the ResponseComparison
//        obj.setSearchTermOne(t1);
//        //set the second search term in the ResponseComparison
//        obj.setSearchTermTwo(t2);
//        //set the count
//        obj.setSearchTermOneResultCount(responseOne.getResponse().getMeta().getHits());
//        //set the count
//        obj.setSearchTermTwoResultCount(responseTwo.getResponse().getMeta().getHits());
//        return obj;
//    }
//
//
//    public void insertNYTSummary(NYTOverview result) {
//        nytMapper.insertSummary(result);
//    }
}
