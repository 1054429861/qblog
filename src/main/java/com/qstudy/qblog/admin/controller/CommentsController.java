package com.qstudy.qblog.admin.controller;

import com.qstudy.qblog.admin.dto.ModifyResult;
import com.qstudy.qblog.admin.dto.PageBean;
import com.qstudy.qblog.admin.entity.Comments;
import com.qstudy.qblog.admin.enums.ModifyEnums;
import com.qstudy.qblog.admin.service.CommentsService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping("/findAllCount")
    public Long findAllCount() {
        return commentsService.findAllCount();
    }

    @RequestMapping("/findAll")
    public List<Comments> findAll() {
        return commentsService.findAll();
    }

    @ResponseBody
    @RequestMapping("/findByPage")
    public PageBean findByPage(Comments comments,
                               @RequestParam(value = "pageCode", required = false) Integer pageCode,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return commentsService.findByPage(comments, pageCode, pageSize);
    }

    @RequestMapping("/findCountByArticleId")
    public Long findCountByArticleId(@RequestParam("articleId") Long articleId) {
        if (articleId != null && articleId != 0) {
            return commentsService.findCountByArticle(articleId);
        }
        return null;
    }

    @RequestMapping("/findCommentsList")
    public PageBean findCommentsList(@RequestParam(value = "pageCode", required = false) Integer pageCode,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                     @RequestParam(value = "articleId", required = false) Integer articleId) {
        if (pageCode != null && pageSize != null && articleId != null && articleId != 0) {
            return commentsService.findCommentsList(pageCode, pageSize, articleId, 0);
        } else {
            return null;
        }
    }

    @RequestMapping("/findById")
    public Comments findById(@RequestParam("id") Long id) {
        if (id != null && id != 0) {
            return commentsService.findById(id);
        } else {
            return null;
        }
    }

    @RequestMapping("/save")
    public ModifyResult save(@RequestBody Comments comments) {
        try {
            commentsService.save(comments);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

    @RequiresUser
    @RequestMapping("/update")
    public ModifyResult update(@RequestBody Comments comments) {
        try {
            commentsService.update(comments);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

    @RequiresUser
    @RequestMapping("/delete")
    public ModifyResult delete(@RequestBody Long... ids) {
        try {
            commentsService.delete(ids);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }
}
