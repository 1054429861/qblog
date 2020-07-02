package com.qstudy.qblog.admin.service.impl;


import com.qstudy.qblog.admin.dto.PageBean;
import com.qstudy.qblog.admin.entity.ArticleTags;
import com.qstudy.qblog.admin.entity.Tags;
import com.qstudy.qblog.admin.enums.ModifyEnums;
import com.qstudy.qblog.admin.exception.ModifyException;
import com.qstudy.qblog.admin.mapper.ArticleTagsMapper;
import com.qstudy.qblog.admin.service.ArticleTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Service
@SuppressWarnings("all")
public class ArticleTagsServiceImpl implements ArticleTagsService {

    @Autowired
    private ArticleTagsMapper articleTagsMapper;

    @Override
    public Long findAllCount() {
        return null;
    }

    @Override
    public List<ArticleTags> findAll() {
        return null;
    }

    @Override
    public PageBean findByPage(ArticleTags articleTags, int pageCode, int pageSize) {
        return null;
    }

    @Override
    public ArticleTags findById(long id) {
        return null;
    }

    @Override
    public void save(ArticleTags articleTags) {
        try {
            if (!exists(articleTags)) {
                int saveCount = articleTagsMapper.save(articleTags);
                if (saveCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
                }
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    private boolean exists(ArticleTags articleTags) {
        return articleTagsMapper.exists(articleTags.getArticleId(), articleTags.getTagsId());
    }

    @Override
    public void update(ArticleTags articleTags) {

    }

    /**
     * @param ids tags-id
     */
    @Override
    public void delete(Long... ids) {
        try {
            for (long id : ids) {
                int deleteCount = articleTagsMapper.delete(id);
                if (deleteCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
                }
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public List<Tags> findByArticleId(long articleId) {
        return articleTagsMapper.findByArticleId(articleId);
    }
}
