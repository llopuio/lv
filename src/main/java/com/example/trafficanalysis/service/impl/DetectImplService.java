package com.example.trafficanalysis.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.trafficanalysis.entity.DayDemo;
import com.example.trafficanalysis.mapper.DetectMapper;
import com.example.trafficanalysis.service.DetectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("DetectService")
public class DetectImplService extends ServiceImpl<DetectMapper, DayDemo> implements DetectService {
    @Autowired
    private DetectMapper detectMapper;

    @Override
    public List<Map<String, Integer>> getEventDetection(String dateType, String year) {
        return detectMapper.getEventDetection(dateType,year);
    }

    @Override
    public JSONObject getEventPredict(String feature) {
        JSONObject jsonObject = new JSONObject();
        List<Map<String,String>> listPredict = detectMapper.getPredict(feature);
        List<Map<String,String>> listTrue = detectMapper.getTrue(feature);
        jsonObject.put("predict",listPredict);
        jsonObject.put("true",listTrue);
        //return detectMapper.getEventPredict(feature);
        return jsonObject;
    }

    @Override
    public List<Map<String, String>> getPredict(String feature) {
        return detectMapper.getPredict(feature);
    }

    @Override
    public List<Map<String, String>> getTrue(String feature) {
        return detectMapper.getTrue(feature);
    }
}
