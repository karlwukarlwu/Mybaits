package org.mapper;

import org.entity.IdenCard;

/**
 * Karl Rules!
 * 2023/11/18
 * now File Encoding is UTF-8
 */
public interface IdenCardMapper {
    //根据id获取到身份证序列号
    public IdenCard getIdenCardById(Integer id);

    //如果听到这里，你可以自己完成，就更好..
    //根据id获取到身份证序列号, 并查询级联的person
    public IdenCard getIdenCardById2(Integer id);
}
