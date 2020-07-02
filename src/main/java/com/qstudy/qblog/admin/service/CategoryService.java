package com.qstudy.qblog.admin.service;


import com.qstudy.qblog.admin.entity.Category;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
public interface CategoryService extends BaseService<Category> {

    Category findByName(String name);
}
