package com.hspedu.mapper;

import com.hspedu.entity.Pet;

import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public interface PetMapper {

    //通过User的id来获取pet对象，可能有多个，因此使用List接收
    public List<Pet> getPetByUserId(Integer userId);
    //通过pet的id获取Pet对象, 同时会查询到pet对象关联的user对象
    public Pet getPetById(Integer id);
}
