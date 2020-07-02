package com.qstudy.qblog.admin.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qstudy.qblog.admin.dto.PageBean;
import com.qstudy.qblog.admin.dto.PasswordHelper;
import com.qstudy.qblog.admin.entity.User;
import com.qstudy.qblog.admin.enums.ModifyEnums;
import com.qstudy.qblog.admin.exception.ModifyException;
import com.qstudy.qblog.admin.mapper.UserMapper;
import com.qstudy.qblog.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author qxl
 * @createTime 2020年06月20日
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired UserMapper userMapper;
    @Autowired PasswordHelper passwordHelper;

    @Override
    public User findByName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public Long findAllCount() {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override  //根分页设置查询用户页数
    public PageBean findByPage(User user, int pageCode, int pageSize) {
        // 设置分页
        PageHelper.startPage(pageCode,pageSize);
        // 执行分页查询
        Page page = userMapper.findByPage(user);
        return new PageBean(page.getTotal(),page.getResult());
    }

    @Override
    public User findById(long id) {
        return userMapper.findById(id);
    }

    @Override //添加用户
    public void save(User user) {
        // 捕获异常
        try {
            // 对密码进行加密
            passwordHelper.encryptPassword(user);
            // 执行插入 失败抛出操作失败异常
            if (userMapper.save(user) <= 0){
                throw new ModifyException(ModifyEnums.ERROR);
            }
        } catch (Exception e) {
           throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }

    @Override //更新用户 合理化更新
    public void update(User user) {
        // 捕获异常
        try {
            // 判断更新后密码是否为空
            if (null != user.getPassword() && !"".equals(user.getPassword())){
                // 对密码加密
                passwordHelper.encryptPassword(user);
            }
            // 执行更新  失败抛出操作失败异常
            if (userMapper.update(user) <= 0)
                throw new ModifyException(ModifyEnums.ERROR);
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }

    }

    @Override //批量删除用户
    public void delete(Long... ids) {
        //捕获异常
        try {
            // 遍历每个id
            for (long id : ids) {
                // 执行删除  失败抛出操作失败异常
                if (userMapper.delete(id) <= 0)
                    throw new ModifyException(ModifyEnums.ERROR);
            }
        } catch (Exception e) {
            throw new ModifyException(ModifyEnums.INNER_ERROR);
        }
    }
}
