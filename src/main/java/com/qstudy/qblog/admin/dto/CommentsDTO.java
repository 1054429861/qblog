package com.qstudy.qblog.admin.dto;



import com.qstudy.qblog.admin.entity.Comments;

import java.io.Serializable;
import java.util.List;

/**
 * 封装评论信息的DTO
 * @author qxl
 * @createTime 2020年06月20日
 */
public class CommentsDTO implements Serializable {

    private Comments parent; //父级留言信息
    private List<Comments> childrenList; //所有子级回复、评论列表

    public CommentsDTO() {
    }

    public CommentsDTO(Comments parent, List<Comments> childrenList) {
        this.parent = parent;
        this.childrenList = childrenList;
    }

    public Comments getParent() {
        return parent;
    }

    public void setParent(Comments parent) {
        this.parent = parent;
    }

    public List<Comments> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Comments> childrenList) {
        this.childrenList = childrenList;
    }

    @Override
    public String toString() {
        return "CommentsDTO{" +
                "parent=" + parent +
                ", childrenList=" + childrenList +
                '}';
    }
}
