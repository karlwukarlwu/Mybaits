package com.hspedu.test;

import com.hspedu.entity.Monster;
import com.hspedu.hspmybatis.config.MapperBean;
import com.hspedu.hspmybatis.sqlsession.*;
import com.hspedu.mapper.MonsterMapper;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class HspMyBatisTest {

    @Test
    public void build() {
        HspConfiguration hspConfiguration = new HspConfiguration();
        Connection connection = hspConfiguration.build("hsp_mybatis.xml");
        System.out.println("connection--" + connection);
    }


    @Test
    public void query() {
        Executor executor = new HspExecutor();
        Monster monster =
                executor.query("select * from monster where id=?", 1);
        System.out.println("monster-- " + monster);
    }

    //完成测试
    @Test
    public void selectOne() {
        HspSqlSession hspSqlSession = new HspSqlSession();
        Monster monster =
                hspSqlSession.selectOne("select * from monster where id=?", 1);
        System.out.println("monster--" + monster);
    }

    @Test
    public void readMapper() {

        HspConfiguration hspConfiguration = new HspConfiguration();
        MapperBean mapperBean =
                hspConfiguration.readMapper("MonsterMapper.xml");
        System.out.println("mapperBean---" + mapperBean);
        System.out.println("ok~~");
    }

    @Test
    public void getMapper() {
        HspSqlSession hspSqlSession = new HspSqlSession();
        MonsterMapper mapper = hspSqlSession.getMapper(MonsterMapper.class);
        System.out.println("mapper= " + mapper.getClass());//mapper是代理对象
        Monster monster = mapper.getMonsterById(1);
        System.out.println("monster--" + monster);
    }

    @Test
    public void openSession() {

        HspSqlSession hspSqlSession = HspSessionFactory.openSession();
        MonsterMapper mapper = hspSqlSession.getMapper(MonsterMapper.class);
        Monster monster = mapper.getMonsterById(1);
        System.out.println("monster===" + monster);
    }
}
