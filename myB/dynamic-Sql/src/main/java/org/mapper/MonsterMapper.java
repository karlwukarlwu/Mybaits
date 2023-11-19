package org.mapper;

/**
 * @author 韩顺平
 * @version 1.0
 */



import org.apache.ibatis.annotations.Param;
import org.entity.Monster;

import java.util.List;
import java.util.Map;

/**
 * 1. 这是一个接口
 * 2. 该接口用于声明操作monster表的方法
 * 3. 这些方法可以通过注解或者xml文件来实现
 */
//在这里写你需要的方法
//    在xml配置显示你的方法的sql语句
public interface MonsterMapper {

    //根据age查询结果
    public List<Monster> findMonsterByAge(@Param(value = "age") Integer age);

    //根据id和名字来查询结果
    public List<Monster> findMonsterByIdAndName(Monster monster);

    //测试choose标签的使用
    public List<Monster> findMonsterByIdOrName_choose(Map<String, Object> map);

    //测试foreach的标签使用
    public List<Monster> findMonsterById_forEach(Map<String, Object> map);

    //trim标签的使用
    public List<Monster> findMonsterByName_Trim(Map<String, Object> map);

    //测试Set标签
    public void updateMonster_set(Map<String, Object> map);
}
