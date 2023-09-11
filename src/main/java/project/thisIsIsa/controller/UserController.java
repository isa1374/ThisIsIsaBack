package project.thisIsIsa.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.thisIsIsa.repository.UserRepository;
import project.thisIsIsa.model.Users;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository UserRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        return UserRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody Users getUserById(@RequestParam Integer id) {
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
    public @ResponseBody String addUser(@RequestParam String name, @RequestParam String password,
            @RequestParam String email, @RequestParam Boolean admin, @RequestParam Integer created_by,
            @RequestParam Integer modified_by, @RequestParam String photo) {
        String message = "";
        Date currentDate = new Date();
        Users user = new Users();
        if ((name != null && !name.equals("")) && (password != null && !password.equals(""))
                && (email != null && !email.equals("")) && (admin != null) && (created_by != null)
                && (modified_by != null)) {
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);
            user.setActive(true);
            user.setAdmin(admin);
            user.setModifiedBy(modified_by);
            user.setPhoto(photo);
            user.setCreated(currentDate);
            user.setModified(currentDate);

            try {
                UserRepository.save(user);
                message = "User saved";
            } catch (Exception e) {
                message = "User couldn't be created";
            }
        } else {
            message = "Couldn't create user. Missing values of properties";
        }

        return message;
    }
    
    @PutMapping(path = "/update")
    public @ResponseBody String updateUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String password,
            @RequestParam String email, @RequestParam Boolean active, @RequestParam Boolean admin,
            @RequestParam Integer modified_by, @RequestParam String photo, @RequestParam Date modified) {
        String message = "";
        Optional<Users> userCapsule = UserRepository.findById(id);
        Users user;
        if (userCapsule.isPresent()) {
            user = userCapsule.get();
            if ((!name.equals("") || name != null) && (!user.getName().equalsIgnoreCase(name))) {
                user.setName(name);
            }
            if ((!password.equals("") || password != null) && (!user.getPassword().equals(password))) {
                user.setPassword(password);
            }
            if ((!email.equals("") || email != null) && (!user.getEmail().equals(email))) {
                user.setEmail(email);
            }
            if (active != null && user.getActive() != active) {
                user.setActive(active);
            }
            if (admin != null && user.getAdmin() != admin) {
                user.setAdmin(admin);
            }
            if (modified_by != null && user.getModifiedBy() != modified_by) {
                user.setModifiedBy(modified_by);
            }
            if ((!photo.equalsIgnoreCase("") || photo != null) && !user.getPhoto().equals(photo)) {
                user.setPhoto(photo);
            }
            if (modified != null && (modified.compareTo(user.getModified()) != 0)) {
                user.setModified(modified);
            }
            try {
                UserRepository.save(user);
                message = "User updated";
            } catch (Exception e) {
                message = "User couldn't be updated";
            }
        } else {
            message = "User couldn't be found";
        }

        return message;
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
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
}

