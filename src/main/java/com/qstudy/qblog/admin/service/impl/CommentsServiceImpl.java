package com.qstudy.qblog.admin.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qstudy.qblog.admin.dto.CommentsDTO;
import com.qstudy.qblog.admin.dto.PageBean;
import com.qstudy.qblog.admin.entity.Comments;
import com.qstudy.qblog.admin.enums.ModifyEnums;
import com.qstudy.qblog.admin.exception.ModifyException;
import com.qstudy.qblog.admin.mapper.CommentsMapper;
import com.qstudy.qblog.admin.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Service
@SuppressWarnings("all")
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public Long findAllCount() {
        return commentsMapper.findAllCount();
    }

    @Override
    public List<Comments> findAll() {
        return commentsMapper.findAll();
    }

    @Override
    public PageBean findByPage(Comments comments, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page page = commentsMapper.findByPage(comments);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public PageBean findCommentsList(int pageCode, int pageSize, int articleId, int sort) {
        PageHelper.startPage(pageCode, pageSize);

        Page<Comments> page = commentsMapper.findAllId(articleId, sort);

        List<Comments> list = commentsMapper.findCommentsList(articleId, sort);

        List<CommentsDTO> commentsDTOS = new ArrayList<CommentsDTO>();

        /**
         * 封装结果类型结构：
         *      [{{Comments-Parent}, [{Comments-Children}, {Comments-Children}...]}, {{}, [{}, {}, {}...]}]
         */
        for (Comments comments : list) {
            List<Comments> commentsList = new ArrayList<Comments>();
            if (comments.getpId() == 0 && comments.getcId() == 0) {
                //说明是顶层的文章留言信息
                for (Comments parent : list) {
                    if (parent.getpId() != 0) {
                        if (parent.getpId() == comments.getId()) {
                            //说明属于当前父节点
                            commentsList.add(parent);
                        }
                    }
                }
                commentsDTOS.add(new CommentsDTO(comments, commentsList));
            }
        }
        if (page.getTotal() < (pageCode * pageSize) - 1) {
            return new PageBean(page.getTotal(), commentsDTOS.subList((pageCode - 1) * pageSize, commentsDTOS.size()));
        } else {
            return new PageBean(page.getTotal(), commentsDTOS.subList((pageCode - 1) * pageSize, (pageCode * pageSize) - 1));
        }
    }

    @Override
    public Long findCountByArticle(long articleId) {
        return commentsMapper.findCountByArticleId(articleId);
    }

    @Override
    public Comments findById(long id) {
        return commentsMapper.findById(id);
    }

    @Override
    public void save(Comments comments) {
        try {
            int saveCount = commentsMapper.save(comments);
            if (saveCount <= 0) {
                throw new ModifyException(ModifyEnums.INNER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.ERROR);
        }
    }

    @Override
    public void update(Comments comments) {
        try {
            int updateCount = commentsMapper.update(comments);
            if (updateCount <= 0) {
                throw new ModifyException(ModifyEnums.INNER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.ERROR);
        }
    }

    @Override
    public void delete(Long... ids) {
        try {
            for (long id : ids) {
                int deleteCount = commentsMapper.delete(id);
                if (deleteCount <= 0) {
                    throw new ModifyException(ModifyEnums.INNER_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.ERROR);
        }
    }


}
