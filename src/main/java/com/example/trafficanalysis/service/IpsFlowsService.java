package com.example.trafficanalysis.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.trafficanalysis.entity.IpsFlows;
import com.example.trafficanalysis.entity.vo.FlowsNum;

import java.text.ParseException;
import java.util.List;

public interface IpsFlowsService extends IService<IpsFlows> {

    List<Integer> getIpsOfFlowsNum();

    Page FrequentModeList(Integer pageNum, Integer pageSize, String datetime) throws ParseException;

    JSONObject getOrganizationOfFrequency(String date, String srcIP, String protocolType, Integer threshold);
}
