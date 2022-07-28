package com.example.trafficanalysis.entity.vo;

import lombok.Data;

@Data
public class PatrolPattern {
    String latitude;
    String longtitude;
    Integer height;
    Float bytes;
    Float nodes;
    String date;
}
