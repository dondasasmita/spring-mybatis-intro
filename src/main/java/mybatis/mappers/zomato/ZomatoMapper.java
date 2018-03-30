package mybatis.mappers.zomato;

import mybatis.model.zomato.CityOverview;
import mybatis.model.zomato.ZomatoCollectionsOverview;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZomatoMapper {

    String INSERT_CITY_ID = "INSERT INTO `mybatis-test`.zomato_city_id (`city_id`,`city_name`, `country_name`, `state_name`, `state_code`)" +
            "VALUES ( #{city_id} , #{city_name}, #{country_name}, #{state_name}, #{state_code})";

    String INSERT_COLLECTION = "INSERT INTO `mybatis-test.zomato_collections (`city_id`, `city_name`, `collection_id`, `title`, `description`, `url`" +
            "VALUES (#{city_id}, #{city_name), #{collection_id), #{title}, #{description}, #{url}";

    @Insert(INSERT_CITY_ID)
    public int insertCityID(CityOverview object);

//    @Insert(INSERT_COLLECTION)
//    public int insertCollections(ZomatoCollectionsOverview object);
}
