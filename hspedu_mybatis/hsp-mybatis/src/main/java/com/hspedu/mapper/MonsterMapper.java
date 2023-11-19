package com.hspedu.mapper;

import com.hspedu.entity.Monster;

/**
 * @author 韩顺平
 * @version 1.0
 * MonsterMapper: 声明对db的crud方法
 */
public interface MonsterMapper {

    //查询方法
    public Monster getMonsterById(Integer id);


}
