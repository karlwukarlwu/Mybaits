package org.mapper;

import org.apache.ibatis.annotations.*;
import org.entity.Pet;

import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 */
public interface PetMapperAnnotation {

    //通过User的id来获取pet对象，可能有多个，因此使用List接收

    /**
     * 1、通过User的id来获取pet对象，可能有多个，因此使用List接收
     * 2、public List<Pet> getPetByUserId(Integer userId);
     * 3. 完成的思路和前面大体相同.
     * <resultMap id="PetResultMap" type="Pet">
     * <id property="id" column="id"/>
     * <result property="nickname" column="nickname"/>
     * <association property="user" column="user_id"
     * select="com.hspedu.mapper.UserMapper.getUserById" />
     * </resultMap>
     * <select id="getPetByUserId" parameterType="Integer" resultMap="PetResultMap">
     * SELECT * FROM `mybatis_pet` WHERE `user_id` = #{userId}
     * </select>
     */

    //id = "PetResultMap" 就是给我们的Results[Result Map] 指定一个名字
    //,目的是为了后面复用
    @Select("SELECT * FROM `mybatis_pet` WHERE `user_id` = #{userId}")
    @Results(id = "PetResultMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "user",
                    column = "user_id",
                    one = @One(select = "org.mapper.UserMapperAnnotation.getUserById"))

    })
    public List<Pet> getPetByUserId(Integer userId);


    //通过pet的id获取Pet对象, 同时会查询到pet对象关联的user对象

    /**
     * <select id="getPetById"
     * parameterType="Integer"
     * resultMap="PetResultMap">
     * SELECT * FROM `mybatis_pet` WHERE `id` = #{id}
     * </select>
     *
     * @ResultMap("PetResultMap") 使用/引用我们上面定义的 Results[ResultMap]
     */
    @Select("SELECT * FROM `mybatis_pet` WHERE `id` = #{id}")
//    results的复用
    @ResultMap("PetResultMap")
    public Pet getPetById(Integer id);
}
