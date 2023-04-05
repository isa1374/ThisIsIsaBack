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

import project.thisIsIsa.model.Material;
import project.thisIsIsa.repository.MaterialRepository;

@Controller
@RequestMapping(path = "/material")
public class MaterialController {
    @Autowired
    private MaterialRepository MaterialRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Material> getAllMaterials() {
        return MaterialRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody Material getMaterialById(@RequestParam Integer id) {
        Material material;
        if (id != null) {
            try {
                Optional<Material> materialCapsule = MaterialRepository.findById(id);
                if (materialCapsule.isPresent()) {
                    material = materialCapsule.get();
                } else {
                    material = null;
                }
            } catch (Exception e) {
                material = null;
            }
        } else {
            material = null;
        }
        return material;
    }
    
    @PostMapping(path = "/add")
    public @ResponseBody String addMaterial(@RequestParam String name, @RequestParam String description,
            @RequestParam Integer created_by, @RequestParam Integer modified_by) {
        String message = "";
        Date currentDate = new Date();
        Material material = new Material();
        if ((name != null && !name.equals("")) && (created_by != null) && (modified_by != null)) {
            material.setName(name);
            material.setDescription(description);
            material.setCreatedBy(created_by);
            material.setModifiedBy(modified_by);
            material.setCreated(currentDate);
            material.setModified(currentDate);
            try {
                MaterialRepository.save(material);
            } catch (Exception e) {
                message = "Couldn't add material";
            }
        } else {
            message = "Couldn't add materials. Missing values of properties";
        }

        return message;
    }
    
    @PutMapping(path = "/update")
    public @ResponseBody String updateMaterial(@RequestParam Integer id, @RequestParam String name,
            @RequestParam String description, @RequestParam Integer modified_by) {
        String message = "";
        if (id != null) {
            Optional<Material> materialCapsule = MaterialRepository.findById(id);
            if (materialCapsule.isPresent()) {
                Material material = materialCapsule.get();
                Date currentDate = new Date();
                if (name != null && !name.equalsIgnoreCase(material.getName())) {
                    material.setName(name);
                }
                if (description != null && !description.equalsIgnoreCase(material.getDescription())) {
                    material.setDescription(description);
                }
                if (modified_by != null) {
                    material.setModifiedBy(modified_by);
                }
                material.setModified(currentDate);
                try {
                    MaterialRepository.save(material);
                    message = "Material updated";
                } catch (Exception e) {
                    message = "Couldn't update material";
                }

            } else {
                message = "Couldn't find material to update";
            }
        } else {
            message = "Couldn't update material. Id missing.";
        }

        return message;
    }
    
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteMaterial(@RequestParam Integer id) {
        String message = "";
        if (id != null) {
            try{
                MaterialRepository.deleteById(id);
                message = "Material deleted";
            } catch (Exception e) {
                message = "Couldn't delete material"; 
            }
        } else {
            message = "Couldn't delete material. Missing id.";
        }
        return message;
    }
}
