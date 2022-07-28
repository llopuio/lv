package com.example.trafficanalysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("month_node")
public class MonthNode {
    private Integer id;
    private String from;
    private String to;
    private Integer month;
    private Integer labelEvent;
}
