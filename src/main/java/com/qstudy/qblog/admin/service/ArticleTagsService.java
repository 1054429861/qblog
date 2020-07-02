package com.qstudy.qblog.admin.service;


import com.qstudy.qblog.admin.entity.ArticleTags;
import com.qstudy.qblog.admin.entity.Tags;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
public interface ArticleTagsService extends BaseService<ArticleTags> {

    List<Tags> findByArticleId(long articleId);
}
