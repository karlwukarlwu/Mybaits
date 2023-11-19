package com.hspedu.mapper;

/**
 * @author 韩顺平
 * @version 1.0
 */

import com.hspedu.entity.Monster;

import java.util.List;
import java.util.Map;

/**
 * 1. 这是一个接口
 * 2. 该接口用于声明操作monster表的方法
 * 3. 这些方法可以通过注解或者xml文件来实现
 */
public interface MonsterMapper {

    //通过id 或者名字查询
    public List<Monster> findMonsterByNameORId(Monster monster);

    //查询名字中含义'精'妖怪
    public List<Monster> findMonsterByName(String name);

    //查询 id > 10 并且 salary 大于 40, 要求传入的参数是HashMap
    public List<Monster> findMonsterByIdAndSalary_PrameterHashMap(Map<String, Object> map);

    //查询 id > 10 并且 salary 大于 40, 要求传入的参数是HashMap
    public List<Map<String, Object>>
    findMonsterByIdAndSalary_PrameterHashMap_ReturnHashMap(Map<String, Object> map);
}
