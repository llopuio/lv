package com.example.trafficanalysis.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

public class FastJsonHttpMessageConverterEx extends FastJsonHttpMessageConverter {
    public FastJsonHttpMessageConverterEx(){
        //配置fastjson特性
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //正常转换null值
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        this.setFastJsonConfig(fastJsonConfig);
    }
    @Override
    protected boolean supports(Class<?> clazz){
        return super.supports(clazz);
    }
}
