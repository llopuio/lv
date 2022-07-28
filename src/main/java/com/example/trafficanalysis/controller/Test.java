package com.example.trafficanalysis.controller;

import com.example.trafficanalysis.entity.RawData;
import com.example.trafficanalysis.entity.Result;
import com.example.trafficanalysis.service.RawTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traffic")
public class Test {
    @Autowired
    RawTestService rawTestService;

    @GetMapping("/{id}")
    public Result getTest(@PathVariable Integer id){
        RawData byId1 = rawTestService.getById1(id);
        return new Result<RawData>(200,"success",byId1);
    }

}
