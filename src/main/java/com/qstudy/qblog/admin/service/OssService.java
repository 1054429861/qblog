package com.qstudy.qblog.admin.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author qxl
 * @ClassName OssService.java
 * @createTime 2020年07月08日 22:33:00
 */

public interface OssService {
    String upload(MultipartFile file) throws IOException;
}
