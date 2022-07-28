package com.example.trafficanalysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.trafficanalysis.entity.DayDemo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DetectMapper extends BaseMapper<DayDemo> {

    List<Map<String, Integer>> getEventDetection(String dateType, String year);

    //List<Map<String,String>> getEventPredict(String feature);

    List<Map<String, String>> getPredict(String feature);

    List<Map<String, String>> getTrue(String feature);
}
