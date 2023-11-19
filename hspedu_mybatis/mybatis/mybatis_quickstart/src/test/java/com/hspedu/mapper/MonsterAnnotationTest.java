package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class MonsterAnnotationTest {

    //属性
    private SqlSession sqlSession;
    private MonsterAnnotation monsterAnnotation;

    @Before
    public void init() {
        //获取到sqlSession
        sqlSession = MyBatisUtils.getSqlSession();
        monsterAnnotation = sqlSession.getMapper(MonsterAnnotation.class);
        //返回的依然是一个接口的代理对象
        System.out.println("monsterAnnotation--" + monsterAnnotation.getClass());
    }

    @Test
    public void addMonster() {

        Monster monster = new Monster();
        monster.setAge(30);
        monster.setBirthday(new Date());
        monster.setEmail("kate6@qq.com");
        monster.setGender(1);
        monster.setName("狐狸精-100");
        monster.setSalary(1000);
        //使用在接口方法配置注解方式完成对DB操作
        monsterAnnotation.addMonster(monster);
        System.out.println("添加后monster-id-" + monster.getId());

        //如果是增删改, 需要提交事务
        if(sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }

        System.out.println("保存成功...");

    }

    @Test
    public void findAllMonster() {

        //使用接口配置注解的方式操作DB
        List<Monster> allMonster = monsterAnnotation.findAllMonster();
        for (Monster monster : allMonster) {
            System.out.println("monster--" + monster);
        }


        if(sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("查询成功...");
    }
}
