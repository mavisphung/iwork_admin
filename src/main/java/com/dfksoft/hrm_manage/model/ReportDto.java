package com.dfksoft.hrm_manage.model;

import java.util.ArrayList;

import com.dfksoft.hrm_manage.entity.Report;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReportDto {
    
    private Integer status;
    private ArrayList<Report> dataReport;
}
