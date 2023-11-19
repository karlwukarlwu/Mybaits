package org.mapper;

import org.apache.ibatis.annotations.Select;
import org.entity.IdenCard;

/**
 * Karl Rules!
 * 2023/11/18
 * now File Encoding is UTF-8
 */
public interface IdenCardMapperAnnotation {
    //根据id获取到身份证
    //这个方法不需要返回任何级联对象
    @Select("SELECT * FROM `idencard` WHERE `id` = #{id}")
    public IdenCard getIdenCardById(Integer id);
}