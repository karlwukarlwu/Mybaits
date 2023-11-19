package com.hspedu.mapper;

import com.hspedu.entity.Person;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author 韩顺平
 * @version 1.0
 */
public interface PersonMapperAnnotation {
    //这里注解实现方法
    //老师说明： 注解的形式就是对前面xml配置方式的体现
    //这里同学们可以结合前面老师讲解的xml配置时，加入的注释来理解

    @Select("SELECT * FROM `person` WHERE `id` = #{id}")
    @Results({
          @Result(id = true, property = "id", column = "id"),
          @Result(property = "name", column = "name"),
          @Result(property = "card", column = "card_id",
                  one = @One(select = "com.hspedu.mapper.IdenCardMapper.getIdenCardById"))
    })
    public Person getPersonById(Integer id);
}
