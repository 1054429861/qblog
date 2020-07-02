package com.qstudy.qblog.admin.service.impl;


import com.alibaba.fastjson.JSONArray;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qstudy.qblog.admin.dto.ArticleArchives;
import com.qstudy.qblog.admin.dto.PageBean;
import com.qstudy.qblog.admin.entity.*;
import com.qstudy.qblog.admin.enums.ModifyEnums;
import com.qstudy.qblog.admin.exception.ModifyException;
import com.qstudy.qblog.admin.mapper.ArticleMapper;
import com.qstudy.qblog.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Service
@SuppressWarnings("all")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private ArticleTagsService articleTagsService;

    @Override
    public Long findAllCount() {
        return articleMapper.findAllCount();
    }

    @Override
    public List<Article> findAll() {
        return articleMapper.findAll();
    }

    @Override
    public PageBean findByPage(Article article, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page<Article> page = articleMapper.findByPage(article);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public PageBean findByPageForSite(Integer pageCode, Integer pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page<Article> page = articleMapper.findByPageForSite();
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public Article findById(long id) {
        return articleMapper.findById(id);
    }

    @Override
    public void save(Article article) {
        try {
            //如果传递过来的状态是1 代表发布 更改发布时间
            if ("1".equals(article.getState())){
                article.setPublishTime(new Date());
            }
            int saveCount = articleMapper.save(article);
            if (saveCount <= 0) {
                throw new ModifyException(ModifyEnums.ERROR);
            } else {
                long articleId = articleMapper.getLastId();
                if (article.getCategory() != null) {
                    categoryService.save(new Category(article.getCategory())); //save category
                    Category category = categoryService.findByName(article.getCategory()); //get Id
                    articleCategoryService.save(new ArticleCategory(articleId, category.getId())); //save linked info
                }
                if (article.getTags() != null) {
                    List<String> list = (List) JSONArray.parse(article.getTags()); // all tags
                    if (list.size() > 0) {
                        for (String name : list) {
                            tagsService.save(new Tags(name));
                            Tags tags = tagsService.findByName(name);
                            if (tags != null) {
                                // insert success
                                articleTagsService.save(new ArticleTags(articleId, tags.getId()));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public void update(Article article) {
        try {
            // 如果传递过来的状态是1 代表发布 更改发布时间
            if ("1".equals(article.getState())){
                article.setPublishTime(new Date());
            }
            // 修改编辑时间
            article.setEditTime(new Date());
            int updateCount = articleMapper.update(article);
            if (updateCount <= 0) {
                throw new ModifyException(ModifyEnums.ERROR);
            } else {
                if (article.getCategory() != null) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public void delete(Long... ids) {
        try {
            for (long id : ids) {
                int deleteCount = articleMapper.delete(id);
                if (deleteCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public List<Article> findByCategory(String category) {
        return articleMapper.findByCategory(category);
    }

    @Override
    public List<ArticleArchives> findArchives() {
        List<ArticleArchives> articleArchivesList = new ArrayList<ArticleArchives>();
        try {
            List<String> dates = articleMapper.findArchivesDates();
            for (String date : dates) {
                List<Article> articleList = articleMapper.findArchivesByDate(date);
                ArticleArchives articleArchives = new ArticleArchives(date, articleList);
                articleArchivesList.add(articleArchives);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
        return articleArchivesList;
    }

    @Override
    public List<Article> findFuzzyByTitle(String title) {
        try {
            return articleMapper.findFuzzyByTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public void addEyeCount(long id) {
        try {
            articleMapper.addEyeCount(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }
}
