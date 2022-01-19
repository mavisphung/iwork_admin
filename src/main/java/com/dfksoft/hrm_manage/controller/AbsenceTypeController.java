package com.dfksoft.hrm_manage.controller;

import com.dfksoft.hrm_manage.entity.AbsenceType;
import com.dfksoft.hrm_manage.service.AbsenceTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AbsenceTypeController {
    private final AbsenceTypeService absenceTypeService;

    public AbsenceTypeController(AbsenceTypeService absenceTypeService) {
        this.absenceTypeService = absenceTypeService;
    }

    @RequestMapping("/absence-type")
    public String index(Model model) {
        List<AbsenceType> absenceTypes = absenceTypeService.findAll();

        model.addAttribute("absenceTypes", absenceTypes);

        return "absence-type";
    }

    @RequestMapping("/addNewAbsenceType")
    public String registerAbsenceType(@RequestParam("description") String description,
                                @RequestParam("appliedTo") String appliedTo) {
        try {
            absenceTypeService.createAbsenceType(description, appliedTo);

        } catch (Exception e) {
            return e.getMessage();
        }

        return "redirect:absence-type";
    }

    @RequestMapping(value = "/absenceTypeDetail", method = RequestMethod.GET)
    public String absenceTypeDetail(@RequestParam("id") int id, Model model) {
        // get account id
        AbsenceType absenceType = absenceTypeService.findById(id);

        model.addAttribute("absenceType", absenceType);

        return "absence-type-detail";
    }

    @RequestMapping(value = "/updateAbsenceType", method = RequestMethod.POST)
    public String updateTitle(@RequestParam("id") int id, @RequestParam("description") String description,
                              @RequestParam("appliedTo") String appliedTo,
                              Model model) {
        absenceTypeService.updateAbsenceType(id, description, appliedTo);

        return "redirect:absence-type";
    }
}
