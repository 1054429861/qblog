package com.qstudy.qblog.admin.service;

import com.qstudy.qblog.admin.entity.ArticleCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*UserServiceImpl单元测试*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleCategoryServiceImplTest {

    @Autowired
    ArticleCategoryService articleCategoryService;


    @Test
    public void save(){
        ArticleCategory ac = new ArticleCategory();
        ac.setArticleId(30);
        ac.setCategoryId(3);
        articleCategoryService.save(ac);

    }
    @Test
    public void delete(){

        articleCategoryService.delete(3L);

    }
}
