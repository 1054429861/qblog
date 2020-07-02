package com.qstudy.qblog.admin.service;



import com.qstudy.qblog.admin.dto.PageBean;

import java.util.List;

/**
 * @author qxl
 * @createTime 2020年06月20日
 */
public interface BaseService<T> {

    /**
     * 查询总数量
     *
     * @return
     */
    Long findAllCount();

    /**
     * 查询所有
     *
     * @return
     */
    List<T> findAll();

    /**
     * 分页查询
     *
     * @param t        查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    PageBean findByPage(T t, int pageCode, int pageSize);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    T findById(long id);

    /**
     * 保存
     *
     * @param t
     */
    void save(T t);

    /**
     * 更新
     *
     * @param t
     */
    void update(T t);

    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long... ids);


}
