package mybatis.mappers.zomato;

import mybatis.model.zomato.CityOverview;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZomatoMapper {

    String INSERT_CITY_ID = "INSERT INTO `mybatis-test`.zomato_city_id (`city_id`,`city_name`)" +
            "VALUES ( #{city_id} , #{city_name})";

    @Insert(INSERT_CITY_ID)
    public int insertCityID(CityOverview object);
}
