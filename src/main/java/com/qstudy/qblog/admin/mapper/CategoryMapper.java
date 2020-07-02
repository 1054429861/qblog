package com.qstudy.qblog.admin.mapper;


import com.github.pagehelper.Page;
import com.qstudy.qblog.admin.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Mapper
public interface CategoryMapper {

    List<Category> findAll();

    Page findByPage(Category category);

    Category findById(long id);

    int save(Category category);

    int update(Category category);

    int delete(long id);

    boolean exists(String name);

    Category findByName(String name);
}
