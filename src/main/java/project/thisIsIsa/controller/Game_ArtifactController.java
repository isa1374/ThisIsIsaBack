package project.thisIsIsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.thisIsIsa.model.Game_Artifact;
import project.thisIsIsa.repository.Game_ArtifactRepository;

@Controller
@RequestMapping(path = "/game_artifact")
public class Game_ArtifactController {

    @Autowired
    private Game_ArtifactRepository game_ArtifactRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Game_Artifact> getGameArtifacts() {
        return game_ArtifactRepository.findAll();
    }
    
}
