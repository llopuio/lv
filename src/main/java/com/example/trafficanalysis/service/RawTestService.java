package com.example.trafficanalysis.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.trafficanalysis.entity.IpsFlows;
import com.example.trafficanalysis.entity.RawData;
import com.example.trafficanalysis.entity.vo.*;
import io.swagger.models.auth.In;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RawTestService extends IService<RawData> {

    RawData getById1(Integer id);
    Page list(Integer pageNum, Integer pageSize);
    //传入几个参数呢？将labelEvent类型改成string类型不知道好使不
    Page SingleList(Integer pageNum, Integer pageSize,String datetime, String labelEvent) throws ParseException;

    List<Map<String, Object>> getEventMap();
    List<Integer> getEventList();

    Page GroupList(Integer pageNum, Integer pageSize,String datetime, String labelEvent) throws ParseException;

    List<EventsChange> getEventsChange(String startTime, String endTime);

    List<FlowSequence> getFlowSequence();
    List<TrafficChange> getTrafficChange(String type);

    Integer getEquipment(String type, String datetime);
    Integer getImportantAsset(String type, String datetime);
    Integer getEventsCount(String type, String datetime);
    Integer getImportantAssetEvents(String type, String datetime);
    //频繁模式查询
    Page FrequentModeList(Integer pageNum, Integer pageSize, String datetime) throws ParseException;
    //事件季节模式查询
    List<FlowSequence> getSeasonalPattern(String eventType);
    //List<PatrolPattern> getPatrolPattern(String date);
    //巡逻模式
    Page getPatrolPattern(Integer pageNum, Integer pageSize, String datetime) throws ParseException;
    //获取IP列表
    List<String> getIPList();
    //获取连接
    List<HashMap<String,String>> getLink(String srcIp);
    //获取链接
    JSONObject getLinks(String srcIp);
    //获取节点组织关系
    JSONObject getOrganizationNode(String datetime,Integer label_event);

    //获取会话次数对应的IP对数量
    //List<FlowsNum> getIpsOfFlowsNum(String protocolType);


}


