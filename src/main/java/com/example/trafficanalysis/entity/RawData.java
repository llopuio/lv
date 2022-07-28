package com.example.trafficanalysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//mybatis-plus会默认将pojo类名当作表名，如果类名和表名不一致，可以使用注解TableName
//
@Data
@TableName("raw_data")
public class RawData {
    private Integer id;
    private Date datetime;
    private Integer serviceId;
    private String srcIp;
    private String srcMac;
    private Float bytes;
    private Float flows;
    private Float packs;
    private Float duration;
    private String dstIp;
    private String protocol;
    private String latitude;
    private String longtitude;
    private Integer height;
    private Integer labelEvent;
    private Integer labelUser;
    private Integer labelPosition1;
    private Integer labelPosition2;
    private Integer frequency;
}
