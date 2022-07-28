package com.example.trafficanalysis.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("ips_flows")
public class IpsFlows {
    private String srcIP;
    private String dstIP;
    private Integer tcp;
    private Integer udp;
    private Integer total;
    private Date day;
    private Integer frequency;

}
