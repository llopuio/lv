package com.example.trafficanalysis.controller;

import com.example.trafficanalysis.common.CommonResult;
import com.example.trafficanalysis.service.RawTestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/traffic")
public class TrafficController {
    @Autowired
    RawTestService rawTestService;
    @ApiOperation(value = "信息统计")
    @RequestMapping(value = "/getEquipment",method = RequestMethod.GET)
    public CommonResult getEventsChange(@RequestParam(value = "type",defaultValue = "day")String timeType,
                                        @RequestParam(value = "dateTime",defaultValue = "2019-01-01")String dateTime){
        //return CommonResult.success(rawTestService.getEventsChange(startTime,endTime));
        //添加到一个List当中
        List<Object> result = new ArrayList<>();
        //初始化HashMap，记录timeType类型对应的格式
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("day","\"%Y-%m-%d\"");
        map.put("month","\"%Y-%m-%d\"");
        map.put("year","\"%Y\"");
        result.add(rawTestService.getEquipment(timeType,dateTime));
        result.add(rawTestService.getImportantAsset(map.get(timeType),dateTime));
        Integer EventsCount = rawTestService.getEventsCount(map.get(timeType),dateTime);
        //result.add(rawTestService.getEventsCount(map.get(timeType),dateTime));
        result.add(EventsCount);
        result.add((double)rawTestService.getImportantAssetEvents(map.get(timeType),dateTime)/EventsCount);
        //return CommonResult.success(rawTestService.getEquipment("month","2019-01"));
        return CommonResult.success(result);
    }
}
