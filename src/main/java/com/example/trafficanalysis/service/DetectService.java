package com.example.trafficanalysis.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.trafficanalysis.entity.DayDemo;

import java.util.List;
import java.util.Map;

public interface DetectService extends IService<DayDemo> {
    List<Map<String,Integer>> getEventDetection(String dateType, String year);

    JSONObject getEventPredict(String feature);

    List<Map<String,String>> getPredict(String feature);
    List<Map<String,String>> getTrue(String feature);
}
