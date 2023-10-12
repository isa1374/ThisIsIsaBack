package project.thisIsIsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import project.thisIsIsa.model.Artifact;
import project.thisIsIsa.repository.ArtifactRepository;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(path = "/artifact")
public class ArtifactController {

    @Autowired
    private ArtifactRepository ArtifactRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Artifact> getAllArtifacts() {
        return ArtifactRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody Artifact getArtifactById(@RequestParam(value = "id") Integer id){
        Optional<Artifact> artifactCapsule;
        Artifact artifact = null;
        if(id != null){
            artifactCapsule = ArtifactRepository.findById(id);
            if(artifactCapsule.isPresent()){
                artifact = artifactCapsule.get();
            }
        }
        return artifact;
    }

    @GetMapping(path = "/getByName")
    public @ResponseBody Iterable<Artifact> getArtifactByName(@RequestParam(value = "name") String name){
        Set<Artifact> artifacts = new HashSet<>();
        if(name != null){
            artifacts = ArtifactRepository.findArtifactByName(name);
        }
        return artifacts;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addArtifact (@RequestParam(value = "name") String name,
        @RequestParam(value = "description", required = false) String description,
        @RequestParam(value = "created_by") Integer createdBy,
        @RequestParam(value = "modified_by") Integer modifiedBy){
        String message ="";
        Artifact artifact = new Artifact();
        Date currentDate = new Date();
        if((name != null || name.equals(""))&& createdBy != null && modifiedBy != null){
            artifact.setName(name);
            artifact.setCreatedBy(createdBy);
            artifact.setModifiedBy(modifiedBy);
            artifact.setCreated(currentDate);
            artifact.setModified(currentDate);
            if(!description.equals("") || description != null){
                artifact.setDescription(description);
            }
            try{
                ArtifactRepository.save(artifact);
                message = "Artifact added.\n" + artifact.toString();
            }catch(Exception e){
                message = "Couldn't add artifact.";
            }
        }else{
            message = "Couldn't add artifact for there are missing values for required properties.";
        }
        return message;
    }

    @PutMapping(path = "/update")
    public @ResponseBody String updateArtifact(@RequestParam(value = "id") Integer id,
                                               @RequestParam(value = "name") String name,
                                               @RequestParam(value = "description") String description,
                                               @RequestParam(value = "modified_by") Integer modifiedBy){
        String message="";
        Optional<Artifact> artifactCapsule = ArtifactRepository.findById(id);
        Artifact artifact;
        Date currentDate = new Date();
        if(artifactCapsule.isPresent()){
            artifact = artifactCapsule.get();
            if(name != null){
                if (name.equals("") || artifact.getName().equalsIgnoreCase(name)) {
                    artifact.setName(artifact.getName());
                } else {
                    artifact.setName(name);
                }
            }else{
                artifact.setName(artifact.getName());
            }
            if(description != null) {
                if (description.equals("") || artifact.getDescription().equalsIgnoreCase(description)) {
                    artifact.setDescription(artifact.getDescription());
                } else {
                    artifact.setDescription(description);
                }
            }else{
                artifact.setDescription(artifact.getDescription());
            }
            if(modifiedBy != null){
                artifact.setModifiedBy(modifiedBy);
            }
            artifact.setModified(currentDate);
            try{
                ArtifactRepository.save(artifact);
                message = "Artifact updated.\n" +  artifact.toString();
            }catch(Exception e){
                message = "Couldn't update the artifact.";
            }
        }else{
            message = "Artifact couldn't be found.";
        }
        return message;
    }
    
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteArtifact(@RequestParam(value = "id") Integer id){
        String message = "";
        if(id != null){
           try{
               ArtifactRepository.deleteById(id);
           }catch (Exception e){
               message = "Artifact couldn't be deleted.";
           }
        }else{
            message = "Missing ID to find artifact.";
        }
        return message;
    }
}
