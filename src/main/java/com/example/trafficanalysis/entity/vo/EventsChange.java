package com.example.trafficanalysis.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class EventsChange {
    private Date day;
    private Integer labelEvent;
    private Integer count;
}
