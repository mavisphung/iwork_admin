package com.dfksoft.hrm_manage.service;

import com.dfksoft.hrm_manage.entity.Title;
import com.dfksoft.hrm_manage.entity.data.TitleData;
import com.dfksoft.hrm_manage.repository.TitleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TitleService {
    private final TitleRepository titleRepository;

    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public List<Title> getAllTitle() {
        return (List<Title>) titleRepository.findAll();
    }

    public List<TitleData> getAllTitleData() {
        List<Title> titles = getAllTitle();
        List<TitleData> titleDatas = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            LocalTime timeAllow = LocalTime.ofSecondOfDay(Math.round(titles.get(i).getTimeAllow()/1000));
            TitleData titleData = new TitleData(titles.get(i).getId(), titles.get(i).getTitleName(), titles.get(i).getDescription(), timeAllow);
            titleDatas.add(titleData);
        }
        return titleDatas;
    }

    public Title findById(int id) {
        return titleRepository.findById(id);
    }

    public TitleData findTitleDataById(int id) {
        Title title = findById(id);
        LocalTime timeAllow = LocalTime.ofSecondOfDay(Math.round(title.getTimeAllow()/1000));
        TitleData titleData = new TitleData(title.getId(), title.getTitleName(), title.getDescription(), timeAllow);
        return titleData;
    }

    public Title createTitle(String titleName, String description, LocalTime timeAllow) {
        Title title = new Title();
        title.setTitleName(titleName);
        title.setDescription(description);
        title.setTimeAllow(timeAllow.toSecondOfDay()*1000);
        return titleRepository.saveAndFlush(title);
    }

    public Title updateTitle(int id, String titleName, String description, LocalTime timeAllow) {
        Title title = titleRepository.findById(id);
        if (title != null) {
            title.setTitleName(titleName);
            title.setDescription(description);
            title.setTimeAllow(timeAllow.toSecondOfDay()*1000);
            return titleRepository.saveAndFlush(title);
        } else {
            return title;
        }
    }
}
