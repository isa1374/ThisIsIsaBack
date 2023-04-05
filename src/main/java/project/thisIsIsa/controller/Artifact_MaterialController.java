package project.thisIsIsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.thisIsIsa.model.Artifact_Material;
import project.thisIsIsa.repository.Artifact_MaterialRepository;

@Controller
@RequestMapping(path = "/artifact_material")
public class Artifact_MaterialController {

    @Autowired
    private Artifact_MaterialRepository artifact_MaterialRepository;
    
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Artifact_Material> getArtifactMaterials() {
        return artifact_MaterialRepository.findAll();
    }
    
}
