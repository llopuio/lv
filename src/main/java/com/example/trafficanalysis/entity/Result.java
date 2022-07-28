package com.example.trafficanalysis.entity;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer i, String success, T byId1) {
        this.code = i;
        this.message=success;
        this.data = byId1;
    }
}
