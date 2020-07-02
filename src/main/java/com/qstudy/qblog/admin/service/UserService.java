package com.qstudy.qblog.admin.service;

import com.qstudy.qblog.admin.entity.User;
/**
 * @author qxl
 * @createTime 2020年06月20日
 */
public interface UserService  extends BaseService<User>{
    User findByName(String username);
}
