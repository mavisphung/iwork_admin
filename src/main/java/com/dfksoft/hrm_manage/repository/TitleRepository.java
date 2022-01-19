package com.dfksoft.hrm_manage.repository;

import com.dfksoft.hrm_manage.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {
    Title findById(int id);
}
