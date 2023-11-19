package org.example;

import org.apache.ibatis.session.SqlSession;
import org.entity.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mapper.MonsterMapper;
import org.util.MyBatisUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Karl Rules!
 * 2023/11/18
 * now File Encoding is UTF-8
 */
public class ForT {
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
    public void findMonsterByAge() {
        List<Monster> monsters =
                monsterMapper.findMonsterByAge(20);
        for (Monster monster : monsters) {
            System.out.println("monster--" + monster);
        }
        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功~");
    }

    @Test
    public void findMonsterByIdAndName() {
        Monster monster = new Monster();
        monster.setId(1);
        monster.setName("牛魔王-100");

        List<Monster> monsters = monsterMapper.findMonsterByIdAndName(monster);
        for (Monster m : monsters) {
            System.out.println("m--" + m);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功~");
    }
    @Test
    public void findMonsterByIdOrName_choose() {

        Map<String, Object> map = new HashMap<>();
        //map.put("name", "牛魔王-100");
        map.put("id", -1);

        List<Monster> monsters = monsterMapper.findMonsterByIdOrName_choose(map);
        for (Monster monster : monsters) {
            System.out.println("monster--" + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功~");
    }

    @Test
    public void findMonsterById_forEach() {

        Map<String, Object> map = new HashMap<>();
        map.put("ids", Arrays.asList(10, 12, 14));

        List<Monster> monsters =
                monsterMapper.findMonsterById_forEach(map);

        for (Monster monster : monsters) {
            System.out.println("monster--" + monster);
        }


        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功~");

    }



    @Test
    public void findMonsterByName_Trim() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "牛魔王-100");
        map.put("age", 10);

        List<Monster> monsters =
                monsterMapper.findMonsterByName_Trim(map);

        for (Monster monster : monsters) {
            System.out.println("monster--" + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功~");

    }
    @Test
    public void updateMonster_set() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("name", "撒旦发射点发射点5-100");
        map.put("age", 76);
        map.put("email", "hsp@qq.com");

        monsterMapper.updateMonster_set(map);

        //修改需要有commit
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println("操作成功~");
    }


}
