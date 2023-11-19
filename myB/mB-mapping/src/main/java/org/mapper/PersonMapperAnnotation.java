package org.mapper;


import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.entity.Person;

/**
 * @author 韩顺平
 * @version 1.0
 */
public interface PersonMapperAnnotation {
    //这里注解实现方法
    //老师说明： 注解的形式就是对前面xml配置方式的体现
    //这里同学们可以结合前面老师讲解的xml配置时，加入的注释来理解

    @Select("SELECT * FROM `person` WHERE `id` = #{id}")
//        <id property="id" column="id"/>
//        <result property="name" column="name"/>
//        <association property="card" column="card_id"
//                     select="com.hspedu.mapper.IdenCardMapper.getIdenCardById"/>
    @Results({// 不需要强调返回值和方法名了 因为方法已经有了返回值和方法名
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "card", column = "card_id",
//                    一对一用one 一对多用many
//                    @One(select = "方法全路径")
                    one = @One(select = "org.mapper.IdenCardMapper.getIdenCardById"))
    })
    public Person getPersonById(Integer id);
}
