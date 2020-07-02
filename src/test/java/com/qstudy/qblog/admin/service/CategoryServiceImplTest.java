package com.qstudy.qblog.admin.service;

import com.qstudy.qblog.admin.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    CategoryService categoryService;
    @Test
    public void findAll(){
        List<Category> all = categoryService.findAll();
        for (Category category : all) {
            long id = category.getId();
            String s = category.getcName();
            System.out.println("===========================");
            System.out.println(id+s);
            System.out.println("===========================");
        }
    }
    @Test
    public void save() {
        Category category = new Category();
        category.setcName("随笔111");
        categoryService.save(category);

    }
    @Test
    public void findById() {
        Category byId = categoryService.findById(2);
        System.out.println(byId.toString());

    }
}
