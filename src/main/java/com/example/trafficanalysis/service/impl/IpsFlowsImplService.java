package com.example.trafficanalysis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.trafficanalysis.entity.IpsFlows;
import com.example.trafficanalysis.entity.vo.FlowsNum;
import com.example.trafficanalysis.mapper.IpsFlowsMapper;
import com.example.trafficanalysis.service.IpsFlowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("IpsFlowsService")
public class IpsFlowsImplService extends ServiceImpl<IpsFlowsMapper, IpsFlows> implements IpsFlowsService {
    @Autowired
    IpsFlowsMapper ipsFlowsMapper;

    @Override
    public List<Integer> getIpsOfFlowsNum() {
        return ipsFlowsMapper.getIpsOfFlowsNum();
    }

    @Override
    public Page FrequentModeList(Integer pageNum, Integer pageSize, String datetime) {
        Page page = new Page(pageNum, pageSize);
        QueryWrapper<IpsFlows> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("src_ip", "dst_ip", "total", "frequency")
                .eq("day", datetime)
                .orderByDesc("frequency")
                .orderByDesc("total");
        return this.page(page, queryWrapper);
    }

    @Override
    public JSONObject getOrganizationOfFrequency(String date, String srcIP, String protocolType, Integer threshold) {
        JSONObject jsonObject = new JSONObject();
        List<Map<String,String>> dipList = ipsFlowsMapper.getDstIPList(date,srcIP,protocolType,threshold);
        List<HashMap<String,String>> links = new ArrayList<>();
        List<HashMap<String,String>> nodes = new ArrayList<>();
        //HashMap<String,String> map2 = new HashMap<>();
        for(int i=0; i<dipList.size(); i++){
            HashMap<String,String> map1 = new HashMap<>();
            map1.put("from", dipList.get(i).get(protocolType));
            map1.put("to",dipList.get(i).get("dst_ip"));
            links.add(map1);
        }
        for(int i=0;i<dipList.size();i++){
            HashMap<String,String> map2 = new HashMap<>();
            map2.put("id",dipList.get(i).get("dst_ip"));
            map2.put("text",dipList.get(i).get("dst_ip"));
            nodes.add(map2);
        }
        for(int i=0;i<dipList.size();i++){
            HashMap<String,String> map3 = new HashMap<>();
            map3.put("id", dipList.get(i).get(protocolType));
            map3.put("text",dipList.get(i).get(protocolType));
            nodes.add(map3);
        }
        jsonObject.put("nodes",nodes);
        jsonObject.put("links",links);
        return jsonObject;
    }
}
