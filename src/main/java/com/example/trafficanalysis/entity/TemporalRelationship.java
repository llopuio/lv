package com.example.trafficanalysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("temporal_relationship")
public class TemporalRelationship {
    private Integer id;
    private String srcIp;
    private String dstIp;
}
