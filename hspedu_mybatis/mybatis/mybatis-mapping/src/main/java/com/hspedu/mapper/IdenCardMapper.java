package com.hspedu.mapper;

import com.hspedu.entity.IdenCard;

/**
 * @author 韩顺平
 * @version 1.0
 */
public interface IdenCardMapper {
    //根据id获取到身份证序列号
    public IdenCard getIdenCardById(Integer id);

    //如果听到这里，你可以自己完成，就更好..
    //根据id获取到身份证序列号, 并查询级联的person
    public IdenCard getIdenCardById2(Integer id);

}
