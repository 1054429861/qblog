package com.qstudy.qblog.admin.service.impl;

import com.qstudy.qblog.admin.dto.PageBean;
import com.qstudy.qblog.admin.entity.ArticleCategory;
import com.qstudy.qblog.admin.enums.ModifyEnums;
import com.qstudy.qblog.admin.exception.ModifyException;
import com.qstudy.qblog.admin.mapper.ArticleCategoryMapper;
import com.qstudy.qblog.admin.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Service
@SuppressWarnings("all")
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Override
    public Long findAllCount() {
        return null;
    }

    @Override
    public List<ArticleCategory> findAll() {
        return null;
    }

    @Override
    public PageBean findByPage(ArticleCategory articleCategory, int pageCode, int pageSize) {
        return null;
    }

    @Override
    public ArticleCategory findById(long id) {
        return null;
    }

    @Override
    public void save(ArticleCategory articleCategory) {
        try {
            if (!exists(articleCategory)){
                int updateCount = articleCategoryMapper.save(articleCategory);
                if (updateCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    private boolean exists(ArticleCategory articleCategory){
        return articleCategoryMapper.exists(articleCategory.getArticleId(), articleCategory.getCategoryId());
    }

    @Override
    public void update(ArticleCategory articleCategory) {

    }

    /**
     *
     * @param ids category-id
     */
    @Override
    public void delete(Long... ids) {
        try {
            for (long id : ids) {
                int deleteCount = articleCategoryMapper.delete(id);
                if (deleteCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
                }
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }
}
