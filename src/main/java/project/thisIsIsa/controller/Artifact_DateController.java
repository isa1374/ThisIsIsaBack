package project.thisIsIsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.thisIsIsa.model.Artifact_Date;
import project.thisIsIsa.repository.Artifact_DateRepository;

@Controller
@RequestMapping(path = "/artifact_date")
public class Artifact_DateController {
    
    @Autowired
    private Artifact_DateRepository artifact_DateRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Artifact_Date> getArtifactDates() {
        return artifact_DateRepository.findAll(); 
    }
}
