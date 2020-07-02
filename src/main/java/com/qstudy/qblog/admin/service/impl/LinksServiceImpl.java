package com.qstudy.qblog.admin.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qstudy.qblog.admin.dto.PageBean;
import com.qstudy.qblog.admin.entity.Links;
import com.qstudy.qblog.admin.enums.ModifyEnums;
import com.qstudy.qblog.admin.exception.ModifyException;
import com.qstudy.qblog.admin.mapper.LinksMapper;
import com.qstudy.qblog.admin.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Service
@SuppressWarnings("all")
public class LinksServiceImpl implements LinksService {

    @Autowired
    private LinksMapper linksMapper;

    @Override
    public Long findAllCount() {
        return linksMapper.findAllCount();
    }

    @Override
    public List<Links> findAll() {
        return linksMapper.findAll();
    }

    @Override
    public PageBean findByPage(Links links, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page page = linksMapper.findByPage(links);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public Links findById(long id) {
        return linksMapper.findById(id);
    }

    @Override
    public void save(Links links) {
        try {
            int saveCount = linksMapper.save(links);
            if (saveCount <= 0) {
                throw new ModifyException(ModifyEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public void update(Links links) {
        try {
            int updateCount = linksMapper.update(links);
            if (updateCount <= 0) {
                throw new ModifyException(ModifyEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override
    public void delete(Long... ids) {
        try {
            for (long id : ids){
                int deleteCount = linksMapper.delete(id);
                if (deleteCount <= 0) {
                    throw new ModifyException(ModifyEnums.ERROR);
                }
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }
}
