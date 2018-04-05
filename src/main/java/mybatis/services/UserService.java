package mybatis.services;

import mybatis.mappers.UserMapper;
import mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by tanerali on 24/07/2017.
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    //get all users using mybatis
    public ArrayList<User> getAllUsers (){
        return userMapper.getAllUsers();
    }

    //get user by id
    public User getById(int id){
        return userMapper.getByID(id);
    }

    //get all users manually
    public ArrayList<User> getAllUsersManually() {

        //do everything necessary to get resutls from DB and turn them into objects
        //in arraylist and return to controller

        ArrayList<User> users = null;
        try {

            users = new ArrayList<>();

            System.out.println("creating connection");

            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");

            // Setup the connection with the DB
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mybatis-test?" +
                    "user=root&password=CodingNomadsFoEva!&useSSL=false");

            System.out.println("connection succeeded");


            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();

            // execute query and assign it to resulSet
            resultSet = statement.executeQuery("select * from users");

            // iterates through the result set
            while (resultSet.next()) {

                User u = new User();

                // set the instance vars of the User pojo to the resultSet values
                u.setId(resultSet.getInt("id"));
                u.setFirst_name(resultSet.getString("first_name"));
                u.setLast_name(resultSet.getString("last_name"));
                u.setIsActive(resultSet.getInt("isActive"));

                // adding the newly set pojo to the ArrayList
                users.add(u);
            }

        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return users;
    }

    //add new user and generate apiKey
    public User addNew(User user) {
        try{
            user.setApiKey(generateAPI(128));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        userMapper.insertUser(user);
        return userMapper.getByName(user.getFirst_name());
    }

    //this is a method to generate an API Key in a String
    public static String generateAPI(final int keyLen) throws NoSuchAlgorithmException {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keyLen);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();

    }


    //update user by its id
    public User updateById(User user) {
        userMapper.updateUser(user);
        return userMapper.getByName(user.getFirst_name());
    }

    //delete
    public User deleteById(int id) {
        userMapper.deleteUser(id);
        return userMapper.getByID(id);
    }

    //get user by age method
//    public ArrayList<User> getUserByAge(int age){
//        return userMapper.getUserByAge(age);
//    }

    //a method to verify the apiKey
    public boolean authenticate(int user_id, String apiKey) {

        boolean apiIsVerified;

        String apiKeyInDB = userMapper.getByID(user_id).getApiKey();

        // check db to see if key is valid and user is active
        if (apiKeyInDB.equalsIgnoreCase(apiKey) && userMapper.getByID(user_id).getIsActive() != 0 ){
            apiIsVerified = true;
        } else apiIsVerified = false;

        return apiIsVerified;
    }




}
