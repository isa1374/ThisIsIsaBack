package project.thisIsIsa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.thisIsIsa.model.Game;
import project.thisIsIsa.repository.GameRepository;

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
    public @ResponseBody Game getGameById(@RequestParam Integer id) {
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

    
}
