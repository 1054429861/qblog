package com.qstudy.qblog.admin.mapper;


import com.qstudy.qblog.admin.entity.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresRoles;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Mapper
public interface ArticleCategoryMapper {

    @RequiresRoles("admin")
    int save(ArticleCategory articleCategory);

    boolean exists(@Param("articleId") long articleId, @Param("categoryId") long categoryId);

    int delete(long id);
}
