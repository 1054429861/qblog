package com.qstudy.qblog.admin.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qstudy.qblog.admin.dto.PageBean;
import com.qstudy.qblog.admin.entity.Article;
import com.qstudy.qblog.admin.entity.Category;
import com.qstudy.qblog.admin.enums.ModifyEnums;
import com.qstudy.qblog.admin.exception.ModifyException;
import com.qstudy.qblog.admin.mapper.CategoryMapper;
import com.qstudy.qblog.admin.service.ArticleCategoryService;
import com.qstudy.qblog.admin.service.ArticleService;
import com.qstudy.qblog.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Service
@SuppressWarnings("all")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Override
    public Long findAllCount() {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public PageBean findByPage(Category category, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page page = categoryMapper.findByPage(category);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public Category findById(long id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void save(Category category) {
        try {
            if (!exists(category)) {
                int saveCount = categoryMapper.save(category);
                if (saveCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
                }
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    // judge, if this category's info is exists, return
    private boolean exists(Category category) {
        return categoryMapper.exists(category.getcName());
    }

    @Override
    public void update(Category category) {
        try {
            if (category.getId() != 0) {
                Category category_old = categoryMapper.findById(category.getId());
                int updateCount = categoryMapper.update(category);
                if (updateCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
                } else {
                    // update tb_article ==> article
                    // find tb_article all this article
                    List<Article> articleList = articleService.findByCategory(category_old.getcName()); // find all related article by category's name
                    for (Article article : articleList) {
                        // get single article info, and update this article's category field's info
                        articleService.update(new Article(article.getId(), category.getcName()));
                    }
                }
            } else {
                throw new ModifyException(ModifyEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public void delete(Long... ids) {
        try {
            if (ids != null && ids.length > 0) {
                for (long id : ids) {
                    // find tb_article all this article
                    Category category = categoryMapper.findById(id); //find category info by id

                    int deleteCount = categoryMapper.delete(id);
                    if (deleteCount <= 0) {
                        throw new ModifyException(ModifyEnums.ERROR);
                    } else {
                        // delete success
                        // delete linked article ==> tb_article_category
                        articleCategoryService.delete(id);

                        // update tb_article ==> article
                        List<Article> articleList = articleService.findByCategory(category.getcName()); // find all related article by category's name
                        for (Article article : articleList) {
                            // get single article info, and update this article's category field's info
                            articleService.update(new Article(article.getId(), "默认分类"));
                        }
                    }
                }
            } else {
                throw new ModifyException(ModifyEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public Category findByName(String name) {
        return categoryMapper.findByName(name);
    }
}
