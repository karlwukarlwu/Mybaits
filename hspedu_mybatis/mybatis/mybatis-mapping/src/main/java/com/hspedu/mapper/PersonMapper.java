package com.hspedu.mapper;

import com.hspedu.entity.Person;

/**
 * @author 韩顺平
 * @version 1.0
 */
public interface PersonMapper {
    //通过Person的id获取到Person,包括这个Person关联的IdenCard对象[级联查询]
    public Person getPersonById(Integer id);

    //通过Person的id获取到Person,包括这个Person关联的IdenCard对象,方式2
    public Person getPersonById2(Integer id);

    //编写方法,通过card_id 查询得到person对象/数据
    public  Person getPersonByCardId(Integer cardId);
}
