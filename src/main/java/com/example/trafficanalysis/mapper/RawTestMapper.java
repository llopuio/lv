package com.example.trafficanalysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.trafficanalysis.entity.EventType;
import com.example.trafficanalysis.entity.RawData;
import com.example.trafficanalysis.entity.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@Mapper
public interface RawTestMapper extends BaseMapper<RawData> {
    IPage<RawData> getById(IPage page, Integer labelEvent);
    RawData getById1(Integer Id);
    EventType getEventList();
    List<EventsChange> getEventsChange(String startTime,String endTime);
    List<FlowSequence> getFlowSequence();
    List<TrafficChange> getTrafficChange(String type);
    List<TrafficChange> getDestinationCount(String type);

    List<String> getEquipmentCountYearSrc(String datetime);
    List<String> getEquipmentCountYearDst(String datetime);
    List<String> getEquipmentCountMonthSrc(String datetime);
    List<String> getEquipmentCountMonthDst(String datetime);
    List<String> getEquipmentCountDaySrc(String datetime);
    List<String> getEquipmentCountDayDst(String datetime);
    List<String> getImportantAssets(String style, String datetime);
    Integer getEventsCount(String style, String datetime);
    Integer getImportantAssetsEvents(String style, String datetime);
    //事件季节模式
    List<FlowSequence> getSeasonalPattern(String eventType);
    //巡逻模式，传递四个参数
    //List<PatrolPattern> getPatrolPattern(String date);
    List<PatrolPattern> getPatrolPattern(String date);
    //获取全局IP列表
    List<String> getIPList();
    //根据选择IP返回其目的IP
    List<String> getLink(String srcIp);
    //
    List<Link> getOrganizationNode(String tablename,Integer date,Integer label_event);

    //获取会话次数对应的IP对数量
    //List<FlowsNum> getIpsOfFlowsNum(String protocolType);

}
