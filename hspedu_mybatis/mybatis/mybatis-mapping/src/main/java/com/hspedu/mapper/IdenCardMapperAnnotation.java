package com.hspedu.mapper;

import com.hspedu.entity.IdenCard;
import org.apache.ibatis.annotations.Select;

/**
 * @author 韩顺平
 * @version 1.0
 * IdenCardMapperAnnotation: 使用注解方式实现1对1的映射
 */
public interface IdenCardMapperAnnotation {
    //根据id获取到身份证
    //这个方法不需要返回任何级联对象
    @Select("SELECT * FROM `idencard` WHERE `id` = #{id}")
    public IdenCard getIdenCardById(Integer id);
}
