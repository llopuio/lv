package com.example.trafficanalysis.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("predict_data")
public class PredictData {
    private DateTime datetime;
    private Float userNum;
    private Float userNumTrue;
    private Float bytes;
    private Float bytesTrue;
    private Float flows;
    private Float flowsTrue;
    private Float packs;
    private Float packsTrue;
}
