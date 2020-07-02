package com.qstudy.qblog.admin.service;

import com.github.pagehelper.Page;
import com.qstudy.qblog.admin.entity.User;
import com.qstudy.qblog.admin.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/*UserServiceImpl单元测试*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

 /*添加用户*/
    @Test
    public void save(){
        int i=0;
        while (i<10) {
            User user = new User();
            user.setUsername("致谢词");
            user.setNickname("哈啊哈");
            user.setPassword("123456");
            user.setSalt("123456");
            user.setEmail("123@qq.com");
            userService.save(user);
            i++;
        }

    }
    /*删除用户*/
    @Test
    public void delete(){
       userService.delete(6L,7L);
    }
    /*更新用户 合理化更新*/
    @Test
    public void updata(){
        User user = new User();
        user.setId(8);
        user.setUsername("gg");
        user.setEmail("123@qq.com");
        userService.update(user);
        System.out.println("==================================");
        System.out.println("==================================");

    }

    @Test
    public void selectByPage(){
        User user = new User();
        user.setUsername("致谢词");
        Page page = userMapper.findByPage(user);
        page.setPageNum(1);
        page.setPageSize(2);
        System.out.println("==================================");
        System.out.println(page);
        System.out.println("==================================");

    }

}
