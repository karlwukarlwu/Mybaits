package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import com.hspedu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class MonsterMapperTest {

    //属性
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    //初始化
    @Before
    public void init() {

        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        System.out.println("monsterMapper=" + monsterMapper.getClass());

    }

    @Test
    public void findMonsterByNameORId() {
        Monster monster = new Monster();
        monster.setId(1);
        monster.setName("狐狸精-100");
        List<Monster> monsters =
                monsterMapper.findMonsterByNameORId(monster);

        for (Monster m : monsters) {
            System.out.println("m-" + m);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("操作成功~");
    }

    @Test
    public void findMonsterByName() {

        List<Monster> monsters = monsterMapper.findMonsterByName("牛魔王");
        for (Monster monster : monsters) {
            System.out.println("monster--" + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("操作成功~");

    }

    @Test
    public void findMonsterByIdAndSalary_PrameterHashMap() {
        //Diamond types are not supported at language level '5'
        //如何解决.=> 在pom.xml文件中指定编译器版本
        /*
             <!--指定Maven编译器 和 jdk版本-->
            <properties>
                <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
                <java.version>1.8</java.version>
            </properties>
         */

        Map<String, Object> map = new HashMap<>();
        map.put("id", 10);
        map.put("salary", 40);
        List<Monster> monsters =
                monsterMapper.findMonsterByIdAndSalary_PrameterHashMap(map);

        for (Monster monster : monsters) {
            System.out.println("monster--" + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("操作成功~");

    }

    @Test
    public void findMonsterByIdAndSalary_PrameterHashMap_ReturnHashMap() {

        Map<String, Object> map = new HashMap<>();
        map.put("id", 10);
        map.put("salary", 40);
        List<Map<String, Object>> monsterList =
                monsterMapper.findMonsterByIdAndSalary_PrameterHashMap_ReturnHashMap(map);

        //取出返回的结果-以map取出
        //回顾java基础,map遍历
        for (Map<String, Object> monsterMap : monsterList) {
            //System.out.println("monsterMap-" + monsterMap);

            //遍历monsterMap(方式1) ,取出属性和对应值
            //Set<String> keys = monsterMap.keySet();
            //for (String key : keys) {
            //    System.out.println(key + "=>" + monsterMap.get(key));
            //}

            //遍历monsterMap(方式2) ,取出属性和对应值
            for (Map.Entry<String, Object> entry : monsterMap.entrySet()) {
                System.out.println(entry.getKey() + "==>" + entry.getValue());
            }
            System.out.println("------------------------");
        }

        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("操作成功~");

    }
}
