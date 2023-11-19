package org.mapper;

import org.entity.Person;

/**
 * Karl Rules!
 * 2023/11/18
 * now File Encoding is UTF-8
 */
public interface PersonMapper {
    //通过Person的id获取到Person,包括这个Person关联的IdenCard对象[级联查询]
    public Person getPersonById(Integer id);
//    另一种方式进行级联查询 这俩方法都是一样的
    //通过Person的id获取到Person,包括这个Person关联的IdenCard对象,方式2
    public Person getPersonById2(Integer id);

}
