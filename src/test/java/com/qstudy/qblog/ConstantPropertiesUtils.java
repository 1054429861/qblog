package com.qstudy.qblog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
@Component
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstantPropertiesUtils {
    //读取配置文件中的oss配置
    @Value("${endpoint}")
    public String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    @Test
    public void test(){
        ConstantPropertiesUtils constantPropertiesUtils = new ConstantPropertiesUtils();
        System.out.println(constantPropertiesUtils.endpoint);
    }

}