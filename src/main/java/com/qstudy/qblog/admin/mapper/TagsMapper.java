package com.qstudy.qblog.admin.mapper;


import com.github.pagehelper.Page;
import com.qstudy.qblog.admin.entity.Tags;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Mapper
public interface TagsMapper {

    List<Tags> findAll();

    Page findByPage(Tags tags);

    Tags findById(long id);

    int save(Tags tags);

    int update(Tags tags);

    int delete(long id);

    boolean exists(String name);

    Tags findByName(String name);

    List<Tags> findByArticleTagsId(long articleId, long tagsId);

    Long findAllCount();
}
