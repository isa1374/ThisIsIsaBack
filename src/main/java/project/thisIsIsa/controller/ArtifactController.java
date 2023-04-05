package project.thisIsIsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.thisIsIsa.model.Artifact;
import project.thisIsIsa.repository.ArtifactRepository;

@Controller
@RequestMapping(path = "/artifact")
public class ArtifactController {

    @Autowired
    private ArtifactRepository ArtifactRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Artifact> getAllArtifacts() {
        return ArtifactRepository.findAll();
    }
    
}
