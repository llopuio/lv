package com.example.trafficanalysis.service.impl;

import cn.hutool.core.lang.hash.Hash;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.trafficanalysis.entity.RawData;
import com.example.trafficanalysis.entity.vo.*;
import com.example.trafficanalysis.mapper.RawTestMapper;
import com.example.trafficanalysis.service.RawTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * service 实现类，继承mp提供通用的service基类
 * serviceImpl<RawTestMapper, RawTest>
 *     2个泛型 1.RawTestMapper Mapper接口
 *            2.RawTest 对应entity
 */

@Service("RawTestService")
public class RawTestImplService extends ServiceImpl<RawTestMapper, RawData> implements RawTestService {
    @Autowired
    private RawTestMapper rawTestMapper;

    public RawData getById1(Integer id){
        return rawTestMapper.getById1(id);
    }

    public Page list(Integer pageNum, Integer pageSize){
        Page page = new Page(pageNum, pageSize);
        return this.page(page);
    }

    @Override
    public Page SingleList(Integer pageNum, Integer pageSize, String datetime, String labelEvent) throws ParseException {
        Page page = new Page(pageNum,pageSize);
        QueryWrapper<RawData> queryWrapper = new QueryWrapper<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String startTime = simpleDateFormat.format(datetime);
        String startTime = datetime + " 00:00:00";
        String endTime = datetime + " 59:59:59";
        //String endTime = simpleDateFormat.format(datetime + " 23:59:59");
        //
        Date startDate = simpleDateFormat.parse(startTime);
        Date endDate = simpleDateFormat.parse(endTime);
        if (labelEvent == null){
            queryWrapper.ge("datetime",startDate)
                    .le("datetime",endDate);
        }else{
            queryWrapper.eq("label_event",labelEvent)
                    .ge("datetime",startDate)
                    .le("datetime",endDate);
        }
        /*
        queryWrapper.eq("label_event",labelEvent)
                .ge("datetime",startDate)
                .le("datetime",endDate);
         */
        /*
        queryWrapper.lambda()
                .eq(RawTest::getLabelEvent,labelEvent)
                .ge(RawTest::getDatetime,startDate)
                .le(RawTest::getDatetime,endDate);

         */
        return this.page(page,queryWrapper);
    }


    @Override
    public List<Map<String, Object>> getEventMap() {
        QueryWrapper<RawData> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("label_event")
                .groupBy("label_event");
        return this.listMaps(queryWrapper);
    }

    @Override
    public List<Integer> getEventList() {
        List<Integer> list = new ArrayList<>();
        QueryWrapper<RawData> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("label_event,count(*) as count")
                .groupBy("label_event");
        List<Map<String,Object>> map = this.getEventMap();
        //对map做处理
        for(Map m: map){
            list.add((Integer) m.get("label_event"));
        }
        return list;
    }

    @Override
    public Page GroupList(Integer pageNum, Integer pageSize, String datetime, String labelEvent) throws ParseException {
        Page page = new Page(pageNum,pageSize);
        QueryWrapper<RawData> queryWrapper = new QueryWrapper<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String startTime = simpleDateFormat.format(datetime);
        String startTime = datetime + " 00:00:00";
        String endTime = datetime + " 59:59:59";
        //String endTime = simpleDateFormat.format(datetime + " 23:59:59");
        //
        Date startDate = simpleDateFormat.parse(startTime);
        Date endDate = simpleDateFormat.parse(endTime);
        if (labelEvent == null){
            queryWrapper.ge("datetime",startDate)
                    .le("datetime",endDate);
        }else{
            queryWrapper.eq("label_event",labelEvent)
                    .ge("datetime",startDate)
                    .le("datetime",endDate);
        }
        return this.page(page,queryWrapper);
    }

    @Override
    public List<EventsChange> getEventsChange(String startTime, String endTime) {
        return rawTestMapper.getEventsChange(startTime,endTime);
    }

    @Override
    public List<FlowSequence> getFlowSequence() {
        return rawTestMapper.getFlowSequence();
    }

    @Override
    public List<TrafficChange> getTrafficChange(String type) {
        if(type.equals("dst_ip")){
            return rawTestMapper.getDestinationCount(type);
        }else{
            return rawTestMapper.getTrafficChange(type);
        }
    }

    @Override
    public Integer getEquipment(String type, String datetime) {
        if(type.equals("year")){
            List<String> collect = Stream.of(getEquipmentYearSrc(datetime),getEquipmentYearDst(datetime))
                    .flatMap(Collection::stream)
                    .distinct()
                    .collect(Collectors.toList());
            return collect.size();
        }else if (type.equals("month")){
            List<String> collect = Stream.of(rawTestMapper.getEquipmentCountMonthSrc(datetime),rawTestMapper.getEquipmentCountMonthDst(datetime))
                    .flatMap(Collection::stream)
                    .distinct()
                    .collect(Collectors.toList());
            return collect.size();
        }else{
            List<String> collect = Stream.of(rawTestMapper.getEquipmentCountDaySrc(datetime),rawTestMapper.getEquipmentCountDayDst(datetime))
                    .flatMap(Collection::stream)
                    .distinct()
                    .collect(Collectors.toList());
            return collect.size();
        }

    }

    @Override
    //这里暂时先只计算目的IP的数量
    public Integer getImportantAsset(String type, String datetime) {
        return rawTestMapper.getImportantAssets(type,datetime).size();
    }

    @Override
    public Integer getEventsCount(String type, String datetime) {
        return rawTestMapper.getEventsCount(type,datetime);
    }

    @Override
    public Integer getImportantAssetEvents(String type, String datetime) {
        return rawTestMapper.getImportantAssetsEvents(type,datetime);
    }

    @Override
    public Page FrequentModeList(Integer pageNum, Integer pageSize, String datetime) throws ParseException {
        Page page = new Page(pageNum,pageSize);
        QueryWrapper<RawData> queryWrapper = new QueryWrapper<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = datetime + " 00:00:00";
        String endTime = datetime + " 59:59:59";
        Date startDate = simpleDateFormat.parse(startTime);
        Date endDate = simpleDateFormat.parse(endTime);
        //暂时查询的是IP，后续应该换成频繁模式，frequency
        queryWrapper.select("src_ip","dst_ip","flows","frequency")
                .ge("datetime",startDate)
                .le("datetime",endDate)
                .orderByDesc("frequency")
                .orderByDesc("flows");
                //.eq("frequency",1);
        /*
        queryWrapper.eq("label_event",labelEvent)
                .ge("datetime",startDate)
                .le("datetime",endDate);
         */
        /*
        queryWrapper.lambda()
                .eq(RawTest::getLabelEvent,labelEvent)
                .ge(RawTest::getDatetime,startDate)
                .le(RawTest::getDatetime,endDate);

         */
        return this.page(page,queryWrapper);
    }
    @Override
    public List<FlowSequence> getSeasonalPattern(String eventType) {
        return rawTestMapper.getSeasonalPattern(eventType);
    }
    //查询巡逻模式，由于一年数据过多，临时计算平均值不方便，因此使用计算好的固定值
    @Override
    public Page getPatrolPattern(Integer pageNum, Integer pageSize, String datetime) throws ParseException {
        Page page = new Page(pageNum,pageSize);
        QueryWrapper<RawData> queryWrapper = new QueryWrapper<>();

        queryWrapper.select("latitude","longtitude","height","sum(flows) as flows","count(*) as packs","DATE_FORMAT(datetime,\"%Y-%m-%d\") as datetime")
                .eq("DATE_FORMAT(datetime,\"%Y-%m-%d\")",datetime)
                .groupBy("latitude","longtitude","height")
                .having("sum(flows) > {0}",3.6);
        return this.page(page,queryWrapper);
    }

    /*
    @Override
    public List<PatrolPattern> getPatrolPattern(String date) {
        return rawTestMapper.getPatrolPattern(date);
    }*/

    @Override
    public List<String> getIPList() {
        return rawTestMapper.getIPList();
    }

    @Override
    //List里面是一个HashMap
    public List<HashMap<String, String>> getLink(String srcIp) {
        List<HashMap<String,String>> result = new ArrayList<>();
        List<String> list = rawTestMapper.getLink(srcIp);
        list.size();
        HashMap<String,String> map = new HashMap<>();
        map.put("from",srcIp);
        map.put("to",list.get(0));
        result.add(map);
        for (int i=0;i<list.size()-1;i++){
            map.put("from",list.get(i));
            map.put("to",list.get(i+1));
            result.add(map);
        }

        return result;
    }

    public JSONObject getLinks(String srcIp){
        JSONObject jsonObject = new JSONObject();
        List<HashMap<String,String>> link = new ArrayList<>();
        List<HashMap<String,String>> nodes = new ArrayList<>();
        List<String> list = rawTestMapper.getLink(srcIp);
        List<String> list2 = new ArrayList<>();
        list2.add(srcIp);
        //再搞一个map?
        HashMap<String,Integer> sum = new HashMap<>();
        sum.put(srcIp,1);
        for(int i=0;i<list.size();i++){
            int count = sum.getOrDefault(list.get(i),0);
            if(count != 0){
                list2.add(list.get(i) + "("+count+")");
                sum.put(list.get(i),++count);
            }else{
                list2.add(list.get(i));
                sum.put(list.get(i),++count);
            }

        }
        list.add(0,srcIp);
        HashMap<String,String> map1 = new HashMap<>();
        map1.put("id",srcIp);
        map1.put("text",srcIp);
        nodes.add(new HashMap<>(map1));
        for(int i=0;i<list2.size();i++){
            map1.put("id",list2.get(i));
            map1.put("text",list2.get(i));
            nodes.add(new HashMap<>(map1));
        }
        HashMap<String,String> map2 = new HashMap<>();
        map2.put("from",srcIp);
        map2.put("to",list2.get(0));
        link.add(new HashMap<>(map2));
        for (int i=0;i<list2.size()-1;i++){
            map2.put("from",list2.get(i));
            map2.put("to",list2.get(i+1));
            link.add(new HashMap<>(map2));
        }
        jsonObject.put("node",nodes);
        jsonObject.put("link",link);
        return jsonObject;
    }

    @Override
    public JSONObject getOrganizationNode(String datetime, Integer label_event) {
        //根据返回值进行分割
        JSONObject jsonObject = new JSONObject();
        String tablename = datetime.split("_")[0]+"_node";
        Integer date = Integer.parseInt(datetime.split("_")[1]);
        List<Link> list = rawTestMapper.getOrganizationNode(tablename,date,label_event);
        //得到Node
        List<HashMap<String,String>> node = new ArrayList<>();
        ///得到Link
        List<HashMap<String,String>> link = new ArrayList<>();
        HashMap<String,String> map1 = new HashMap<>();
        for(int i=0;i<list.size();i++){
            map1.put("id",list.get(i).getFrom());
            map1.put("text",list.get(i).getFrom());
            node.add(new HashMap<>(map1));
            map1.put("id",list.get(i).getTo());
            map1.put("text",list.get(i).getTo());
            node.add(new HashMap<>(map1));
        }
        HashMap<String,String> map2 = new HashMap<>();
        for (int i=0;i<list.size();i++){
            map2.put("from",list.get(i).getFrom());
            map2.put("to",list.get(i).getTo());
            link.add(new HashMap<>(map2));
        }
        jsonObject.put("node",node);
        jsonObject.put("link",link);

        return jsonObject;
    }

    /**
    @Override
    public List<FlowsNum> getIpsOfFlowsNum(String protocolType) {
        return rawTestMapper.getIpsOfFlowsNum(protocolType);
    }
    */


    //得到网络设备信息，一年份的src_ip去重结果
    public List<String> getEquipmentYearSrc(String datetime){

        return rawTestMapper.getEquipmentCountYearSrc(datetime);
    }
    //得到网络设备信息，一年份的dst_ip去重结果
    public List<String> getEquipmentYearDst(String datetime){

        return rawTestMapper.getEquipmentCountYearDst(datetime);
    }
    //得到网络设备信息，某月的src_ip去重结果
    //public List<String> getEquipmentMonthSrc(String)


}


