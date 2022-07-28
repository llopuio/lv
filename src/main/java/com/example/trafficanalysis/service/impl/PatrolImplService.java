package com.example.trafficanalysis.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.trafficanalysis.entity.PatrolData;
import com.example.trafficanalysis.entity.RawData;
import com.example.trafficanalysis.mapper.PatrolMapper;
import com.example.trafficanalysis.service.PatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/***
 * service 实现类，继承mp提供通用的service基类
 * serviceImpl<PatrolMapper, PatrolData>
 *     2个泛型 1.PatrolMapper Mapper接口
 *            2.PatrolData 对应entity
 */
@Service
public class PatrolImplService extends ServiceImpl<PatrolMapper, PatrolData> implements PatrolService {
    @Autowired
    private PatrolMapper patrolMapper;

    @Override
    public Page getPatrolPattern(Integer pageNum, Integer pageSize, String date) throws ParseException {
        Page page = new Page(pageNum, pageSize);
        QueryWrapper<PatrolData> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("latitude", "longitude", "height", "day", "bytes", "nodes", "patrol")
                .eq("day", date)
                .ne("patrol", 2);
        return this.page(page, queryWrapper);
    }

}
