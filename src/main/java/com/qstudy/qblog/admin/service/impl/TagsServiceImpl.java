package com.qstudy.qblog.admin.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qstudy.qblog.admin.dto.PageBean;
import com.qstudy.qblog.admin.entity.Tags;
import com.qstudy.qblog.admin.enums.ModifyEnums;
import com.qstudy.qblog.admin.exception.ModifyException;
import com.qstudy.qblog.admin.mapper.TagsMapper;
import com.qstudy.qblog.admin.service.ArticleTagsService;
import com.qstudy.qblog.admin.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Service
@SuppressWarnings("all")
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private ArticleTagsService articleTagsService;

    @Override
    public Long findAllCount() {
        return tagsMapper.findAllCount();
    }

    @Override
    public List<Tags> findAll() {
        return tagsMapper.findAll();
    }

    @Override
    public PageBean findByPage(Tags tags, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page page = tagsMapper.findByPage(tags);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public Tags findById(long id) {
        return tagsMapper.findById(id);
    }

    @Override
    public void save(Tags tags) {
        try {
            if (!exists(tags)) {
                int saveCount = tagsMapper.save(tags);
                if (saveCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
                }
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    private boolean exists(Tags tags) {
        return tagsMapper.exists(tags.gettName());
    }

    @Override
    public void update(Tags tags) {
        try {
            if (tags.getId() != 0) {
                int updateCount = tagsMapper.update(tags);
                if (updateCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
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

                    int deleteCount = tagsMapper.delete(id);
                    if (deleteCount <= 0) {
                        throw new ModifyException(ModifyEnums.ERROR);
                    } else {
                        // delete success
                        // delete linked article ==> tb_article_tags
                        articleTagsService.delete(id);
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
    public Tags findByName(String name) {
        return tagsMapper.findByName(name);
    }

    @Override
    public List<Tags> findByArticleTagsId(long articleId, long tagsId) {
        return tagsMapper.findByArticleTagsId(articleId, tagsId);
    }
}
