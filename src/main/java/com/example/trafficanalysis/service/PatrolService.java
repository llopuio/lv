package com.example.trafficanalysis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.trafficanalysis.entity.PatrolData;

import java.text.ParseException;
import java.util.List;


//巡逻模式
public interface PatrolService extends IService<PatrolData> {

    Page getPatrolPattern(Integer pageNum, Integer pageSize, String date) throws ParseException;

}
