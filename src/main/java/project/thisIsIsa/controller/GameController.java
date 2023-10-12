package project.thisIsIsa.controller;

import java.time.format.DecimalStyle;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import project.thisIsIsa.model.Game;
import project.thisIsIsa.repository.GameRepository;

import java.util.Set;

@Controller
@RequestMapping(path = "/game")
public class GameController {

    @Autowired
    private GameRepository GameRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Game> getAllGames() {
        return GameRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody Game getGameById(@RequestParam(value = "id") Integer id) {
        Optional<Game> gameCapsule;
        Game game = null;
        if (id != null) {
            gameCapsule = GameRepository.findById(id);
            if (gameCapsule.isPresent()) {
                game = gameCapsule.get();
            }
        }
        return game;
    }

    @GetMapping(path = "/getByName")
    public @ResponseBody Iterable<Game> getGameByName(@RequestParam (value = "name") String name){
        Set<Game> games = new HashSet<Game>();
        if(name != null) games = GameRepository.findGameByName(name);
        return games;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addGame(@RequestParam(value = "name") String name,
                                        @RequestParam(value = "description", required = false) String description,
                                        @RequestParam(value = "created_by") Integer createdBy,
                                        @RequestParam(value = "modified_by") Integer modifiedBy){
        String message = "";
        Date currentDate = new Date();
        Game game = new Game();
        if((name != null || name.equals("")) && (createdBy != null) && (modifiedBy != null)){
            game.setName(name);
            game.setCreated(currentDate);
            game.setModified(currentDate);
            game.setModifiedBy(modifiedBy);
            game.setCreatedBy(createdBy);

            if(description != null || !description.equals("")) game.setDescription(description);
            try{
                GameRepository.save(game);
                message = "Game saved\n" + game.toString();
            }catch(Exception e){
                message = "Game couldn't be saved";
            }
        }else{
            message = "Game couldn't be saved. Missing values of properties.";
        }
        return message;
    }

    @PutMapping(path = "/update")
    public @ResponseBody String updateGame(@RequestParam(value = "id") Integer id,
                                           @RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "description", required = false) String description,
                                           @RequestParam(value = "modified_by") Integer modifiedBy){
        String messsage = "";
        Date currentDate = new Date();
        Optional<Game> gamesCapsule = GameRepository.findById(id);
        Game game;
        if(gamesCapsule.isPresent()){
            game = gamesCapsule.get();
            if(name != null){
                if(name.equals("") || game.getName().equalsIgnoreCase(name)){
                    game.setName(game.getName());
                }else{
                    game.setName(name);
                }
            }else{
                game.setName(game.getName());
            }
            if((description != null)){
                if(game.getDescription().equalsIgnoreCase(description) || description.equals("")) {
                    game.setDescription(game.getDescription());
                }else {
                    game.setDescription(description);
                }
            }else{
                game.setDescription(game.getDescription());
            }
            if(modifiedBy != null && game.getModifiedBy() != modifiedBy){
                game.setModifiedBy(modifiedBy);
            }
            game.setModified(currentDate);

            try{
                GameRepository.save(game);
                messsage = "Game updated\n" +  game.toString();
            }catch (Exception e){
                messsage = "Game couldn't be updated.";
            }
        }else{
            messsage = "Game couldn't be found.";
        }
        return messsage;
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteGame (@RequestParam(value = "id") Integer id){
        String message = "";
        if(id != null) {
            try {
                GameRepository.deleteById(id);
                message = "Game deleted.";
            } catch (Exception e) {
                message = "Game couldn't be deleted.";
            }
        }else{
            message = "ID needed to deleted game.";
        }
        return message;
    }


}
