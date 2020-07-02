package com.qstudy.qblog.admin.mapper;


import com.qstudy.qblog.admin.entity.ArticleTags;
import com.qstudy.qblog.admin.entity.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Mapper
public interface ArticleTagsMapper {

    int save(ArticleTags articleTags);

    boolean exists(@Param("articleId") long articleId, @Param("tagsId") long tagsId);

    int delete(long id);

    List<Tags> findByArticleId(long articleId);
}
