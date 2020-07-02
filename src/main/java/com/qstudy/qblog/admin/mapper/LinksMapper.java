package com.qstudy.qblog.admin.mapper;


import com.github.pagehelper.Page;
import com.qstudy.qblog.admin.entity.Links;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Mapper
public interface LinksMapper {

    List<Links> findAll();

    Page findByPage(Links links);

    Links findById(long id);

    int save(Links links);

    int update(Links links);

    int delete(long id);

    Long findAllCount();
}
