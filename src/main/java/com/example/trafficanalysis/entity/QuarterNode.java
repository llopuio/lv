package com.example.trafficanalysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("quarter_node")
public class QuarterNode {
    private Integer id;
    private String from;
    private String to;
    private Integer quarter;
    private Integer labelEvent;
}
