package com.qstudy.qblog.admin.mapper;


import com.github.pagehelper.Page;
import com.qstudy.qblog.admin.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Mapper
public interface ArticleMapper {

    List<Article> findAll();

    Page<Article> findByPage(Article article);

    Page<Article> findByPageForSite();

    Article findById(long id);

    int save(Article article);

    int update(Article article);

    int delete(long id);

    long getLastId();

    List<Article> findByCategory(String category);

    Long findAllCount();

    List<String> findArchivesDates();

    List<Article> findArchivesByDate(String date);

    List<Article> findFuzzyByTitle(String title);

    void addEyeCount(long id);
}
