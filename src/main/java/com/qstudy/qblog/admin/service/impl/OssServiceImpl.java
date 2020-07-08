package com.qstudy.qblog.admin.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.qstudy.qblog.admin.service.OssService;
import com.qstudy.qblog.admin.utils.ConstantPropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author qxl
 * @ClassName OssServiceImpl.java
 * @createTime 2020年07月08日 22:33:00
 */
@Service
public class OssServiceImpl implements OssService {
    @Autowired
    ConstantPropertiesUtils constantPropertiesUtils;

    @Override
    public String upload(MultipartFile file) throws IOException {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = constantPropertiesUtils.ENDPOINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = constantPropertiesUtils.ACCESSKEYID;
        String accessKeySecret = constantPropertiesUtils.ACCESSKEYSECRET;
        String bucketname = constantPropertiesUtils.BUCKETNAME;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        InputStream inputStream = file.getInputStream();

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdf.format(d);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String fileUrl = "cover/";
        String fileName = fileUrl + date + uuid + file.getOriginalFilename();

        ossClient.putObject(bucketname, fileName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        //URL https://qblogimg.oss-cn-beijing.aliyuncs.com/img.png
        String url = "https://" + bucketname + "." + endpoint + "/" + fileName;
        return url;
    }
}
