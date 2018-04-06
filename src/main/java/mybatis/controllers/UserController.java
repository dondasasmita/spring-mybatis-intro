package mybatis.controllers;

import java.util.ArrayList;

import mybatis.Exceptions.UnauthorizedException;
import mybatis.model.User;
import mybatis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    //RequestMapping maps URLs to methods

    //Get
    @RequestMapping("/")
    public ArrayList<User> getUsers() {
        return userService.getAllUsers();
    }

    //Get ID and get the result if API Key is a match. Example: localhost8080:/users/4?apikey=<theAPIKEY>
    @RequestMapping("/{id}")
    public User getById(@PathVariable(value="id") int id,
                        @RequestParam(value="apikey") String key) throws UnauthorizedException {

        if(userService.authenticate(id,key)) {
            return userService.getById(id);
        } else throw new UnauthorizedException("Your API Key does not match. Please check again.");
    }

    @RequestMapping("/manual")
    public ArrayList<User> getUsersManually() {
        //write the necessary code to get all users and return
        //them in json to the browser without using mybatis
        return userService.getAllUsersManually();
    }

    //Create
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public User addNew(@RequestBody User user) {
        return userService.addNew(user);
    }

    //Update
    @RequestMapping(method = RequestMethod.PATCH, value = "/")
    public User updateById(@RequestBody User user, @RequestParam(name = "apiKey") String key) throws UnauthorizedException {

        if(userService.authenticate(user.getId(),key)){
            return userService.updateById(user);
        } else throw new UnauthorizedException("Your API Key does not match. Please check again.");

    }

    //Delete
    @RequestMapping(method= RequestMethod.DELETE, value="/")
    public User deleteById(@RequestParam(value="id")int id,
                           @RequestParam(name = "apiKey") String key) throws UnauthorizedException {
        if(userService.authenticate(id,key)) {
            return userService.deleteById(id);
        } else throw new UnauthorizedException("Your API Key does not match. Please check again.");

    }

    //get user by age
//    @RequestMapping("/age")
//    public ArrayList<User> getUsers(@RequestParam(value="age") int age) {
//        return userService.getUserByAge(age);
//    }


}
