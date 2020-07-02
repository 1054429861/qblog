package com.qstudy.qblog.admin.service;


import com.qstudy.qblog.admin.entity.Tags;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
public interface TagsService extends BaseService<Tags> {

    Tags findByName(String name);

    List<Tags> findByArticleTagsId(long articleId, long tagsId);
}
