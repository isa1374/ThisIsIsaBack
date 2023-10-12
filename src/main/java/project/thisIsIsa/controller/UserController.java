package project.thisIsIsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.thisIsIsa.model.Users;
import project.thisIsIsa.repository.UserRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository UserRepository;
    UserController () {}

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        return UserRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody Users getUserById(@RequestParam(value = "id") Integer id) {
        Users user;
        if (id != null) {
            Optional<Users> userCapsule = UserRepository.findById(id);
            if (userCapsule.isPresent()) {
                user = userCapsule.get();
            } else {
                user = null;
            }
        } else {
            user = null;
        }
        return user; 
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addUser(@RequestParam(value = "name") String name, @RequestParam (value = "password") String password,
                                        @RequestParam(value = "email") String email, @RequestParam(value = "admin") Boolean admin, @RequestParam (value = "createdBy") Integer created_by,
                                        @RequestParam (value = "modifiedBy") Integer modified_by, @RequestParam (value = "photo", required = false) String photo) {
        String message = "";
        Date currentDate = new Date();
        Users user = new Users();
        if ((name != null || !name.equals("")) &&
                (password != null || !password.equals("")) &&
                (email != null || !email.equals("")) &&
                (admin != null) && (created_by != null) &&
                (modified_by !=null)) {

            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);
            user.setActive(true);
            user.setAdmin(admin);
            user.setModifiedBy(modified_by);
            user.setCreated(currentDate);
            user.setModified(currentDate);

            if(photo != null || !photo.equalsIgnoreCase(" ")) {
                user.setPhoto(photo);
            }

            try {
                UserRepository.save(user);
                message = "User saved\n" + user.toString();
            } catch (Exception e) {
                message = "User couldn't be created.";
            }
        } else {
            message = "Couldn't create user. Missing values of properties.";
        }

        return message;
    }
    
    @PutMapping(path = "/update")
    public @ResponseBody String updateUser(@RequestParam(value = "id") Integer id, @RequestParam (value = "name", required = false) String name, @RequestParam (value = "password", required = false) String password,
            @RequestParam (value = "email", required = false) String email, @RequestParam (value = "active", required = false) Boolean active, @RequestParam (value = "admin", required = false) Boolean admin,
            @RequestParam (value = "modifiedBy") Integer modified_by, @RequestParam (value = "photo", required = false) String photo) {
        String message = "";
        Optional<Users> userCapsule = UserRepository.findById(id);
        Users user;
        Date currentDate = new Date();
        if (userCapsule.isPresent()) {
            user = userCapsule.get();
            if ((name != null || !name.equals("")) && (!user.getName().equalsIgnoreCase(name))) {
                user.setName(name);
            }
            if ((!password.equals("")) && (!user.getPassword().equals(password))) {
                user.setPassword(password);
            }else{
                user.setPassword(user.getPassword());
            }
            if ((!email.equals("")) && (!user.getEmail().equals(email))) {
                user.setEmail(email);
            }else{
                user.setEmail(user.getEmail());
            }
            if (active != null && user.getActive() != active) {
                user.setActive(active);
            }else{
                user.setActive(user.getActive());
            }
            if (admin != null && user.getAdmin() != admin) {
                user.setAdmin(admin);
            }else{
                user.setAdmin(user.getAdmin());
            }
            if (modified_by != null && user.getModifiedBy() != modified_by) {
                user.setModifiedBy(modified_by);
            }else{
                user.setModifiedBy(user.getModifiedBy());
            }
            if ((!photo.equalsIgnoreCase("")) && ((user.getPhoto() != null) && !user.getPhoto().equals(photo))) {
                user.setPhoto(photo);
            }else{
                user.setPhoto(user.getPhoto());
            }
            user.setModified(currentDate);
            try {
                UserRepository.save(user);
                message = "User updated\n" + user.toString();
            } catch (Exception e) {
                message = "User couldn't be updated";
            }
        } else {
            message = "User couldn't be found";
        }

        return message;
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteUser(@RequestParam(value = "id") Integer id) {
        String message = "";
        if (id != null) {
            try{
                UserRepository.deleteById(id);
                message = "User deleted"; 
            } catch (Exception e) {
                message = "Couldn't delete user";
            }
        } else {
            message = "ID needed to delete user";
        }
        return message;
    }

    @GetMapping(path = "/getUserByName")
    public @ResponseBody Iterable<Users> getUserByName(@RequestParam String name){
        Set<Users> users = new HashSet<>();
        if(name != null) users = UserRepository.findUsersByName(name);
        return users;
    }

    @GetMapping(path = "/getAdmins")
    public @ResponseBody Iterable<Users> getAdmins (){
        Set<Users> users = UserRepository.getListOfAdmins();
        return users;
    }

    @GetMapping(path = "/getActiveUsers")
    public @ResponseBody Iterable<Users> getActiveUsers (){
        Set<Users> users = UserRepository.getListOfActiveUsers();
        return  users;
    }

    @GetMapping(path = "/getInactiveUsers")
    public @ResponseBody Iterable<Users> getInactiveUsers (){
        Set<Users> users = UserRepository.getListOfInactiveUsers();
        return users;
    }

    @GetMapping(path = "/getUsersByEmail")
    public @ResponseBody Iterable<Users> getUsersByEmail(@RequestParam String email){
        Set<Users> users = new HashSet<>();
        if(email != null) users = UserRepository.findUsersByEmail(email);
        return users;
    }
}

