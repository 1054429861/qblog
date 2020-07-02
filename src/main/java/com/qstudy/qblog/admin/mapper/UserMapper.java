package com.qstudy.qblog.admin.mapper;

import com.github.pagehelper.Page;
import com.qstudy.qblog.admin.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Mapper
public interface UserMapper {
    Page findByPage(User user);

    User findById(long id);

    int save(User user);

    int update(User user);

    int delete(long id);

    User findByName(String username);

}
