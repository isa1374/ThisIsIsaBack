package project.thisIsIsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.thisIsIsa.model.Material_Date;
import project.thisIsIsa.repository.Material_DateRepository;

@Controller
@RequestMapping(path = "/material_date")
public class Material_DateController {

    @Autowired
    private Material_DateRepository material_DateRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Material_Date> getMaterialDates() {
        return material_DateRepository.findAll();
    }
    
}
