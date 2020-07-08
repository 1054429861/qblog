package com.qstudy.qblog.admin.controller;

import com.qstudy.qblog.admin.dto.ModifyResult;
import com.qstudy.qblog.admin.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author qxl
 * @ClassName OssController.java
 * @createTime 2020年07月08日 22:30:00
 */
@RestController
@RequestMapping("oss")
public class OssController {
    @Autowired
    OssService ossService;
    @RequestMapping("upload")
    public ModifyResult uploda(@RequestParam("picture") MultipartFile file){
        try {
            String uploadAddr = ossService.upload(file);
            System.out.println("上传成功 地址: "+uploadAddr);
            //将文件在服务器的存储路径返回
            return new ModifyResult(true, uploadAddr);
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return new ModifyResult(false, "上传失败");
        }
    }
}
