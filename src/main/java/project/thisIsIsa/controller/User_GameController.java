package project.thisIsIsa.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.thisIsIsa.model.Game;
import project.thisIsIsa.model.User_Game;
import project.thisIsIsa.model.Users;
import project.thisIsIsa.repository.User_GameRepository;

@Controller
@RequestMapping(path = "/user_game")
public class User_GameController {

    @Autowired
    private User_GameRepository user_GameRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User_Game> getUserGames() {
        return user_GameRepository.findAll();
    }

    @GetMapping (path = "/getByUserId")
    public @ResponseBody Set<User_Game> getByUserId(@RequestParam Integer user_id) {
        Set<User_Game> games;
        try {
            games = user_GameRepository.findByUserId(user_id);
        } catch (Exception e) {
            games = new HashSet<User_Game>();
        }
        return games;
    }
    
    @GetMapping(path = "/getByGameId")
    public @ResponseBody Set<User_Game> getByGameId(@RequestParam Integer game_id) {
        Set<User_Game> users;
        try{
            users = user_GameRepository.findByGameId(game_id);
        } catch (Exception e) {
            users = new HashSet<User_Game>();
        }
        return users; 
    }
    
    @PostMapping(path = "/add")
    public @ResponseBody String addUserGame(@RequestParam Integer user_id, @RequestParam Integer game_id) {
        String message = "";
        if (user_id != null && game_id != null) {
            Set<User_Game> games = getByUserId(user_id);
            Integer listGameID = null;
            Boolean contains = false;
            for (User_Game g : games) {
                listGameID = g.getGame().getId();
                if (listGameID == game_id)
                    contains = true;
            }
            if (!contains) {
                User_Game userGame = new User_Game();
                UserController userController = new UserController();
                GameController gameController = new GameController();
                Users user = userController.getUserById(user_id);
                Game game = gameController.getGameById(game_id);
                userGame.setGame(game);
                userGame.setUser(user);
                try {
                    user_GameRepository.save(userGame);
                    message = "Game " + game.getName() + " saved for " + user.getName();
                } catch (Exception e) {
                    message = "Couldn't save game " + game.getName() + " for " + user.getName();
                }
            } else {
                message = "Game already exists for this user";
            }
        } else {
            message = "Couldn't add game. Id missing.";
        }
        return message;
    }

    @DeleteMapping("/delete")
    public @ResponseBody String deleteUserGame(@RequestParam Integer user_id, @RequestParam Integer game_id) {
        String message = "";
        Set<User_Game> games = getByUserId(user_id);
        if (!games.isEmpty()) {
            for (User_Game game : games) {
                try {
                    user_GameRepository.deleteById(game.getId());
                    message = "Game deleted for user";
                } catch (Exception e) {
                    message = "Couldn't delete game for user";
                }
            }
        } else {
            message = "Couldn't delete game. Game not found for user";
        }
        return message;
    }
    

}
