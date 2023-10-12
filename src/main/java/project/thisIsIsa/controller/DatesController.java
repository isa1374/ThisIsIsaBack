package project.thisIsIsa.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public @ResponseBody Dates getDateById(@RequestParam(value = "id") Integer id) {
        Optional<Dates> dateCapsule;
        Dates date = new Dates();
        if(id != null) {
            dateCapsule = DatesRepository.findById(id);

            if (dateCapsule.isPresent()) {
                date = dateCapsule.get();
            } else {
                date = null;
            }
        }
        return date;
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addDate(@RequestParam(value = "month") String month,
                                        @RequestParam(value = "created_by") Integer created_by,
                                        @RequestParam(value = "modified_by") Integer modified_by) {
        String message = "";
        Date currentDate = new Date();
        Dates date = new Dates();
        if((month != null || month.equals(" ")) && created_by != null && modified_by != null) {
            date.setMonth(month);
            date.setCreatedBy(created_by);
            date.setModifiedBy(modified_by);
            date.setCreated(currentDate);
            date.setModified(currentDate);

            try {
                DatesRepository.save(date);
                message = "Date saved successfully.\n" + date.toString();
            } catch (Exception e) {
                message = "Couldn't save date";
            }
        }

        return message;
    }

    @PutMapping(path = "/update")
    public @ResponseBody String updateDate(@RequestParam(value = "id") Integer id,
                                           @RequestParam(value = "month") String month,
                                           @RequestParam(value = "modified_by") Integer modified_by) {
        String message = "";
        Date currentDate = new Date();
        Optional<Dates> dateCapsule;
        Dates date = new Dates();
        if(id != null) {
            dateCapsule = DatesRepository.findById(id);
            if (dateCapsule.isPresent()) {
                date = dateCapsule.get();
                if (month != null){
                    if(month.equals("") || date.getMonth().equalsIgnoreCase(month)){
                        date.setMonth(date.getMonth());
                    }else{
                        date.setMonth(month);
                    }
                }else{
                    date.setMonth(date.getMonth());
                }

                if (modified_by != null) date.setModifiedBy(modified_by);
                date.setModified(currentDate);
                try {
                    DatesRepository.save(date);
                    message = "Date saved.\n" + date.toString();
                } catch (Exception e) {
                    message = "Date couldn't be saved.";
                }
            } else {
                message = "Date couldn't be found.";
            }
        }else{
            message = "ID needed to find date.";
        }
        return message;
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteDate(@RequestParam int id) {
        String message = "";
        try{
            DatesRepository.deleteById(id);
            message = "Date deleted.";
        } catch (Exception e) {
            message = "Couldn't delete date.";
        }
        return message;
    }

    @GetMapping(path = "/getDateByMonth")
    public @ResponseBody Iterable<Dates> getDateByMonth(@RequestParam(value = "month") String month){
        Set<Dates> dates = new HashSet<>();
        if(month != null || !month.equals(" ")){
            dates = DatesRepository.findDatesByMonth(month);
        }
        return dates;
    }
}
