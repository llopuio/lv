package com.example.trafficanalysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("patrol_data")
public class PatrolData {
    private String latitude;
    private String longitude;
    private Integer height;
    private Integer bytes;
    private Integer nodes;
    private Date day;
    private Integer patrol;
}
