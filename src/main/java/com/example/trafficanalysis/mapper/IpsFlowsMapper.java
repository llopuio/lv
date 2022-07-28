package com.example.trafficanalysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.trafficanalysis.entity.IpsFlows;
import com.example.trafficanalysis.entity.RawData;
import com.example.trafficanalysis.entity.vo.FlowsNum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IpsFlowsMapper extends BaseMapper<IpsFlows> {
    //获取会话次数对应的IP对数量
    List<Integer> getIpsOfFlowsNum();

    //获取源IP对应满足时间、协议以及会话阈值的目的IP
    List<Map<String, String>> getDstIPList(String date, String srcIP, String protocolType, Integer threshold);



}
