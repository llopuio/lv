package com.example.trafficanalysis.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.trafficanalysis.common.CommonPage;
import com.example.trafficanalysis.common.CommonResult;
import com.example.trafficanalysis.entity.IpsFlows;
import com.example.trafficanalysis.entity.RawData;
import com.example.trafficanalysis.entity.vo.PatrolPattern;
import com.example.trafficanalysis.service.DetectService;
import com.example.trafficanalysis.service.IpsFlowsService;
import com.example.trafficanalysis.service.PatrolService;
import com.example.trafficanalysis.service.RawTestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;


//http://localhost:9526/situation/index/
@RestController
@RequestMapping("/situation/index")
public class SituationController {
    @Autowired
    RawTestService rawTestService;
    @Autowired
    IpsFlowsService ipsFlowsService;
    @Autowired
    PatrolService patrolService;
    @Autowired
    DetectService detectService;

    /**
     * url: '/getSingleObject/list/'
     * method: 'get'
     * params:
     *        pageNum: 1,
     *        pageSize；5
     */
    /*
    @ApiOperation(value = "单目标事态分析")
    @RequestMapping(value = "/getSingleObject/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<RawTest>> getList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        Page list = rawTestService.list(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }
     */
    /*
    @ApiOperation(value = "单目标事态分析v2")
    @RequestMapping(value = "/getSingleObject/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<RawData>> getSingleList(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                           @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                           @RequestParam(value = "date",defaultValue = "2022-06-20")String datetime,
                                                           @RequestParam(value = "event_type",defaultValue = "null")String labelEvent) throws ParseException {
        Page test = rawTestService.SingleList(pageNum,pageSize, datetime,labelEvent);
        //Page list = rawTestService.SingleList(pageNum,pageSize, datetime,labelEvent);
        //Page list = rawTestService.SingleList(pageNum,pageSize,@DateTimeFormat(pattern = "yyyy-MM-dd") datetime,labelEvent);
        return CommonResult.success(CommonPage.restPage(test));
    }

    @ApiOperation(value = "获取事件列表")
    @RequestMapping(value = "/getEventList",method = RequestMethod.GET)
    public CommonResult getEventList(){
        return CommonResult.success(rawTestService.getEventList());
    }

    @ApiOperation(value = "群体事件列表")
    @RequestMapping(value = "/getGroupObject/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<RawData>> getGroupList(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                          @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                          @RequestParam(value = "date",defaultValue = "2022-06-20")String datetime,
                                                          @RequestParam(value = "event_type",defaultValue = "null")String labelEvent) throws ParseException {
        Page list = rawTestService.GroupList(pageNum,pageSize,datetime,labelEvent);
        return CommonResult.success(CommonPage.restPage(list));
    }
    @ApiOperation(value = "事件数量变化")
    @RequestMapping(value = "/getEventsChange",method = RequestMethod.GET)
    public CommonResult getEventsChange(@RequestParam(value = "startTime",defaultValue = "2019-01-01")String startTime,
                                        @RequestParam(value = "endTime",defaultValue = "2019-01-30")String endTime){
        return CommonResult.success(rawTestService.getEventsChange(startTime,endTime));
    }
    @ApiOperation(value = "流量时序图")
    @RequestMapping(value = "/getFlowSequence",method = RequestMethod.GET)
    public CommonResult getFlowSequence(){
        return CommonResult.success(rawTestService.getFlowSequence());
    }
    @ApiOperation(value = "事件数量变化底部")
    @RequestMapping(value = "/getTrafficChange",method = RequestMethod.GET)
    public CommonResult getTrafficChange(@RequestParam(value = "type",defaultValue = "bytes")String type){
        return CommonResult.success(rawTestService.getTrafficChange(type));
    }
    */
    /*
    @ApiOperation(value = "通信业务频繁模式")
    @RequestMapping(value = "/getFrequentMode",method = RequestMethod.GET)
    public CommonResult<CommonPage<RawData>> getFrequentMode(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                             @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                             @RequestParam(value = "date",defaultValue = "2019-01-01")String datetime) throws ParseException {
        Page list = rawTestService.FrequentModeList(pageNum,pageSize,datetime);
        return CommonResult.success(CommonPage.restPage(list));
    }
     */
    @ApiOperation(value = "通信频繁模式")
    @RequestMapping(value = "/getFrequentMode", method = RequestMethod.GET)
    public CommonResult<CommonPage<IpsFlows>> getFrequentMode(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                              @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                                              @RequestParam(value = "date",defaultValue = "2019-01-01")String datetime) throws ParseException {
        Page list = ipsFlowsService.FrequentModeList(pageNum, pageSize, datetime);
        return CommonResult.success(CommonPage.restPage(list));
    }


    //加会话次数对应的IP数量,如果两天的相同IP对的会话次数相同，那么该会话次数对应的IP数量为2
    //直接统计会话次数对应的数量，不考虑IP对相同的情况
    @ApiOperation(value = "会话次数对应的IP对数目")
    @RequestMapping(value = "/getIpsOfFlowsNum", method = RequestMethod.GET)
    public CommonResult ipsOfFlowsNum(){
        return CommonResult.success(ipsFlowsService.getIpsOfFlowsNum());
    }

    //事件季节模式需要改接口，应该加一个年份的参数
    @ApiOperation(value = "事件季节模式")
    @RequestMapping(value = "/getSeasonalPattern",method = RequestMethod.GET)
    public CommonResult getSeasonalPattern(@RequestParam(value = "event_type",defaultValue = "0")String labelEvent){
        return CommonResult.success(rawTestService.getSeasonalPattern(labelEvent));
    }
    /*
    @ApiOperation(value = "巡逻模式")
    @RequestMapping(value = "/getPatrolPattern",method = RequestMethod.GET)
    public CommonResult getPatrolPattern(@RequestParam(value = "latitude", defaultValue = "28")String latitude,
                                         @RequestParam(value = "longtitude", defaultValue = "77")String longtitude,
                                         @RequestParam(value = "height", defaultValue = "200")Integer height,
                                         @RequestParam(value = "date", defaultValue = "2019-01-01")String date){
        return CommonResult.success(rawTestService.getPatrolPattern(latitude, longtitude, height, date));
    }
    */
    /*
    @ApiOperation(value = "巡逻模式")
    @RequestMapping(value = "/getPatrolPattern",method = RequestMethod.GET)
    public CommonResult<CommonPage<PatrolPattern>> getPatrolPattern(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                                         @RequestParam(value = "date",defaultValue = "2019-01-01")String date) throws ParseException {
        Page list = rawTestService.getPatrolPattern(pageNum,pageSize,date);
        return CommonResult.success(CommonPage.restPage(list));
    }
     */
    @ApiOperation(value = "通信巡逻模式")
    @RequestMapping(value = "/getPatrolPattern", method = RequestMethod.GET)
    public CommonResult<CommonPage<PatrolPattern>> getPatrolPattern(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
                                                                    @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
                                                                    @RequestParam(value = "date", defaultValue = "2019-01-01")String date) throws ParseException {
        Page list = patrolService.getPatrolPattern(pageNum, pageSize, date);
        return CommonResult.success(CommonPage.restPage(list));
    }



    @ApiOperation(value = "获取IP列表")
    @RequestMapping(value = "/getIpList",method = RequestMethod.GET)
    public CommonResult getIpList(){
        return CommonResult.success(rawTestService.getIPList());
    }

    @ApiOperation(value = "根据IP获取链接")
    @RequestMapping(value = "/getLink",method = RequestMethod.GET)
    public CommonResult getLink(@RequestParam(value = "src_ip",defaultValue = "10.126.37.128") String src_ip){
        /*
        JSONObject jsonObject = new JSONObject();
        List<HashMap<String,String>> list = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        map.put("id",src_ip);
        map.put("text",src_ip);
        list.add(map);
        jsonObject.put("node",list);
        jsonObject.put("link",rawTestService.getLink(src_ip));
         */
        return CommonResult.success(rawTestService.getLinks(src_ip));
    }

    @ApiOperation(value = "节点组织关联模式")
    @RequestMapping(value = "/getOrganizationNode",method = RequestMethod.GET)
    public CommonResult getOrganizationNode(@RequestParam(value = "date",defaultValue = "year_2019")String datetime,
                                            @RequestParam(value = "event_type",defaultValue = "1")Integer label_event){
        return CommonResult.success(rawTestService.getOrganizationNode(datetime, label_event));
    }

    @ApiOperation(value = "频繁模式下的节点组织关系")
    @RequestMapping(value = "/getOrganizationOfFrequency", method = RequestMethod.GET)
    public CommonResult getOrganizationOfFrequency(@RequestParam(value = "date",defaultValue = "2019-01-01")String date,
                                                   @RequestParam(value = "srcIP",defaultValue = "10.100.76.8")String srcIP,
                                                   @RequestParam(value = "protocolType",defaultValue = "total")String protocolType,
                                                   @RequestParam(value = "threshold",defaultValue = "15")Integer threshold){
        return CommonResult.success(ipsFlowsService.getOrganizationOfFrequency(date,srcIP,protocolType,threshold));
    }

    @ApiOperation(value = "事件检测")
    @RequestMapping(value = "/getEventDetect", method = RequestMethod.GET)
    public CommonResult getEventDetect(@RequestParam(value = "dateType", defaultValue = "day")String dateType,
                                       @RequestParam(value = "year", defaultValue = "2019")String year){
        return CommonResult.success(detectService.getEventDetection(dateType, year));
    }

    @ApiOperation(value = "事件预测")
    @RequestMapping(value = "/getEventPredict", method = RequestMethod.GET)
    public CommonResult getEventPredict(@RequestParam(value = "feature",defaultValue = "user_num")String feature){
        return CommonResult.success(detectService.getEventPredict(feature));
    }

    @ApiOperation(value = "文件上传")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file")MultipartFile file){
        if(file.isEmpty()){
            return "文件为空，请重新上传";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "G:\\upload_data";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
