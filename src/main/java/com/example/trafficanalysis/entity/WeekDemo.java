package com.example.trafficanalysis.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("week_demo")
public class WeekDemo {
    private Integer index;
    private Date datetime;
    private Integer userNum;
    private Integer bytes;
    private Integer flows;
    private Integer packs;
    private Integer duration;
    private Integer eventNum;
}
