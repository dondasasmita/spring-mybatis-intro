package mybatis.mappers.weather;

import mybatis.model.weather.Weather;
import mybatis.model.weather.WeatherOverview;
import mybatis.model.weather.WeatherRoot;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WeatherMapper {

   String INSERT_SUMMARY = "INSERT INTO `mybatis-test`.weather (`date`,`city`, `main`, `description`)" +
           "VALUES ( #{date} , #{city}, #{main}, #{description})";
   String GET_BY_ID = "SELECT * FROM `mybatis-test`.weather where id = #{id}";
   String DELETE_ID = "DELETE FROM `mybatis-test`.weather WHERE id = #{id}";
   String UPDATE_ID = "UPDATE `mybatis-test`.weather SET date = #{date}, city = #{city}, main = #{main}, description = #{description} WHERE id = #{id}";



   @Insert(INSERT_SUMMARY)
    public int insertWeather(WeatherOverview summary);

   @Delete(DELETE_ID)
    public int deleteID(int id);

   @Select(GET_BY_ID)
    public Weather getByID(int id);

   @Update(UPDATE_ID)
    public int updateID(Weather weather);


}
