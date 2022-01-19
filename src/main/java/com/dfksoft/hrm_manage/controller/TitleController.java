package com.dfksoft.hrm_manage.controller;

import com.dfksoft.hrm_manage.entity.Title;
import com.dfksoft.hrm_manage.entity.data.TitleData;
import com.dfksoft.hrm_manage.service.TitleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class TitleController {
    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @RequestMapping("/title")
    public String index(Model model) {
        List<TitleData> titles = titleService.getAllTitleData();

        model.addAttribute("titles", titles);

        return "title";
    }

    @RequestMapping("/addNewTitle")
    public String registerTitle(@RequestParam("titleName") String titleName, @RequestParam("description") String description,
                               @RequestParam("timeAllow") String timeAllowString) {
        try {
            LocalTime timeAllow = LocalTime.parse(timeAllowString, DateTimeFormatter.ISO_LOCAL_TIME);
            titleService.createTitle(titleName, description, timeAllow);

        } catch (Exception e) {
            return e.getMessage();
        }

        return "redirect:title";
    }

    @RequestMapping(value = "/titleDetail", method = RequestMethod.GET)
    public String titleDetail(@RequestParam("id") int id, Model model) {
        // get account id
        TitleData title = titleService.findTitleDataById(id);

        model.addAttribute("title", title);

        return "title-detail";
    }

    @RequestMapping(value = "/updateTitle", method = RequestMethod.POST)
    public String updateTitle(@RequestParam("id") int id, @RequestParam("titleName") String titleName, @RequestParam("description") String description,
                             @RequestParam("timeAllow") String timeAllowString,
                             Model model) {
        LocalTime timeAllow = LocalTime.parse(timeAllowString, DateTimeFormatter.ISO_LOCAL_TIME);
        titleService.updateTitle(id, titleName, description, timeAllow);

        return "redirect:title";
    }
}
