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

import project.thisIsIsa.model.Dates;
import project.thisIsIsa.repository.DatesRepository;

@Controller
@RequestMapping(path = "/dates")
public class DatesController {
    @Autowired
    private DatesRepository DatesRepository;
    
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Dates> getAllDates() {
        return DatesRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody Dates getDateById(@RequestParam int id) {
        Optional<Dates> dateCapsule = DatesRepository.findById(id);
        Dates date;
        if (dateCapsule.isPresent()) {
            date = dateCapsule.get();
        } else {
            date = null;
        }
        return date;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addDate(@RequestParam String month, @RequestParam int created_by,
            @RequestParam int modified_by) {
        String message = "";
        Date currentDate = new Date();
        Dates date = new Dates();
        date.setMonth(month);
        date.setCreatedBy(created_by);
        date.setModifiedBy(modified_by);
        date.setCreated(currentDate);
        date.setModified(currentDate);

        try {
            DatesRepository.save(date);
            message = "Date saved successfully";
        } catch (Exception e) {
            message = "Couldn't save date";
        }

        return message;
    }

    @PutMapping(path = "/update")
    public @ResponseBody String updateDate(@RequestParam int id, @RequestParam String month,
    @RequestParam
    int created_by,
            @RequestParam int modified_by) {
        String message = "";
        Date currentDate = new Date();
        Optional<Dates> dateCapsule = DatesRepository.findById(id);
        Dates date;
        if (dateCapsule.isPresent()) {
            date = dateCapsule.get();
            if (!month.equalsIgnoreCase(""))
                date.setMonth(month);
            if (created_by != 0)
                date.setCreatedBy(created_by);
            if (modified_by != 0)
                date.setModifiedBy(modified_by);
            date.setModified(currentDate);
            try {
                DatesRepository.save(date);
                message = "Date: " + date.getMonth() + " saved";
            } catch (Exception e) {
                message = "Date couldn't be saved";
            }
        } else {
            message = "Date couldn't be found";
        }
        return message;
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteDate(@RequestParam int id) {
        String message = "";
        try{
            DatesRepository.deleteById(id);
            message = "Date deleted";
        } catch (Exception e) {
            message = "Couldn't delete date";
        }
        return message;
    }
}
