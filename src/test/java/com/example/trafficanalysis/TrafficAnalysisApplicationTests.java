package com.example.trafficanalysis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.trafficanalysis.entity.RawData;
import com.example.trafficanalysis.mapper.DetectMapper;
import com.example.trafficanalysis.mapper.IpsFlowsMapper;
import com.example.trafficanalysis.mapper.PatrolMapper;
import com.example.trafficanalysis.mapper.RawTestMapper;
import com.example.trafficanalysis.service.DetectService;
import com.example.trafficanalysis.service.IpsFlowsService;
import com.example.trafficanalysis.service.PatrolService;
import com.example.trafficanalysis.service.RawTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SpringBootTest
class TrafficAnalysisApplicationTests {

    @Autowired
    RawTestMapper rawTestMapper;
    @Autowired
    RawTestService rawTestService;
    @Autowired
    IpsFlowsMapper ipsFlowsMapper;
    @Autowired
    IpsFlowsService ipsFlowsService;
    @Autowired
    PatrolMapper patrolMapper;
    @Autowired
    PatrolService patrolService;
    @Autowired
    DetectMapper detectMapper;
    @Autowired
    DetectService detectService;

    @Test
    void query(){
        System.out.println(rawTestMapper.selectById(0));
    }
    @Test
    void query2(){
        RawData rawData = rawTestService.getById(1);
        System.out.println(rawData);
    }
    @Test
    void query3(){
        rawTestMapper.getEquipmentCountYearSrc("2019");
    }
    @Test
    void contextLoads() {
        //MybatisPlus默认的是使用id为主键名的，在对应映射的实体类离，主键字段的头上加上@TableId("commodity_id"),
        //就表示告诉MybatisPlus主键列名为commodity_id
        System.out.println(rawTestMapper.selectById(0));
    }
    @Test
    void page(){
        IPage<RawData> iPage = new Page<>(1,2);
        IPage<RawData> page = rawTestService.page(iPage);
        List<RawData> records = page.getRecords();
        System.out.println(records);
        System.out.println(page.getPages());
    }
    @Test
    void xmlPage(){
        IPage<RawData> iPage = new Page<>(1,2);
        IPage<RawData> byId = rawTestMapper.getById(iPage, 1);
        List<RawData> records = byId.getRecords();
        System.out.println(records);
        System.out.println(byId.getPages());
    }

    @Test
    void queryWrapp(){
        QueryWrapper<RawData> rawTestQueryWrapper = new QueryWrapper<>();
        rawTestQueryWrapper
                .select("src_ip","src_mac","datetime")
                .eq("id",1);
        rawTestService.list(rawTestQueryWrapper);
        System.out.println(rawTestService.list(rawTestQueryWrapper));
    }
    @Test
    void singleObject() throws ParseException {
        QueryWrapper<RawData> queryWrapper = new QueryWrapper<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
        String datetime = "2022-06-20";
        Integer labelEvent = 0;
        String startTime = datetime + " 00:00:00";
        String endTime = datetime + " 23:59:59";

        queryWrapper.lambda()
                .eq(RawData::getLabelEvent,labelEvent)
                .ge(RawData::getDatetime,startTime)
                .le(RawData::getDatetime,endTime);
        rawTestService.list(queryWrapper);

        //String startTime = simpleDateFormat.format(datetime);
        //String endTime = simpleDateFormat.format(datetime + " 23:59:59");
        //
        //会不会是因为日期格式的问题呢?
        /*
        Date startDate = simpleDateFormat.parse(startTime);
        Date endDate = simpleDateFormat.parse(endTime);
        System.out.println(startDate);
        queryWrapper.lambda()
                .eq(RawTest::getLabelEvent,labelEvent)
                .ge(RawTest::getDatetime,startDate)
                .le(RawTest::getDatetime,endDate);
        rawTestService.list(queryWrapper);
         */

    }

    @Test
    void getEventList(){
        //System.out.println(rawTestService.getEventMap());
    }
    @Test
    void getEquipment(){
        //rawTestService.getEquipment("day","2019-01-01");
    }
    @Test
    void getImportAsset(){
        //rawTestMapper.getImportantAssets("\"%Y-%m\"","2019-01");
        //System.out.println(rawTestMapper.getImportantAssetsEvents("\"%Y-%m\"", "2019-01"));
    }
    @Test
    void test() throws ParseException {
        //rawTestMapper.getPatrolPattern("28","77",280,"2019-01-01");
        //QueryWrapper<RawData> queryWrapper = new QueryWrapper<>();
        //queryWrapper
        //rawTestMapper.getPatrolPattern("2019-01-01");
        //ipsFlowsMapper.getIpsOfFlowsNum();
        //patrolService.getPatrolPattern(1, 10, "2019-01-31");
        //System.out.println(detectMapper.getEventDetection("week", "2020"));
        //ipsFlowsService.FrequentModeList(1,10,"2019-01-01");
        //List<Map<String, String>> a = ipsFlowsMapper.getDstIPList("2019-01-01", "10.100.76.8", "total", 15);
        //System.out.println(a);
        //ipsFlowsService.getOrganizationOfFrequency("2019-01-01","10.100.76.8","tcp",30);
        detectService.getEventPredict("user_num");
    }

}
