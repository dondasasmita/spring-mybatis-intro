package mybatis.controllers;

import mybatis.model.zomato.CityRoot;
import mybatis.model.zomato.CollectionsRoot;
import mybatis.services.ZomatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("zomato")

public class ZomatoController {

    @Autowired
    ZomatoService zomatoService;

    @RequestMapping("/getCityID")
    public CityRoot getCityID (@RequestParam(value="city", defaultValue = "Singapore") String query,
                               @RequestParam(value="insert",defaultValue = "false") boolean insert){
        return zomatoService.getCityID(query,insert);
    }

    @RequestMapping("/getCollections")
    public CollectionsRoot getCollections (@RequestParam(value="cityName", defaultValue = "Singapore") String cityName,
                                           @RequestParam(value="insert",defaultValue = "false") boolean insert) {
        return zomatoService.getCollections(cityName,insert);
    }

}
