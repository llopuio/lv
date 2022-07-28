package com.example.trafficanalysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.trafficanalysis.entity.PatrolData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PatrolMapper extends BaseMapper<PatrolData> {


}
