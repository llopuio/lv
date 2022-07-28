package com.example.trafficanalysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("year_node")
public class YearNode {
    private Integer id;
    private String from;
    private String to;
    private Integer year;
    private Integer labelEvent;
}
