package com.hspedu.mapper;

import com.hspedu.entity.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author 韩顺平
 * @version 1.0
 * UserMapperAnnotation：以注解的方式来配置多对一
 */
public interface UserMapperAnnotation {

    //通过id获取User对象

    /**
     * 1. 注解的配置就是对应的Mapper.xml文件配置的，改写
     * 2.
     *     1、一定要想一想我们前面1-1是如何实现
     *     2、配置/实现 public User getUserById(Integer id);
     *     3、思路(1) 先通过user-id 查询得到user信息 (2) 再根据user-id查询对应的pet信息
     *       并映射到User-List<Pet> pets
     *     <resultMap id="UserResultMap" type="User">
     *         <id property="id" column="id"/>
     *         <result property="name" column="name"/>
     *         1. ofType="Pet" 指定返回的集合中存放的数据类型Pet
     *         2. collection 表示 pets 是一个集合
     *         3. property="pets" 是返回的user对象的属性 pets
     *         4. column="id" SELECT * FROM `mybatis_user` WHERE `id` = #{id} 返回的id字段对应的值
     *         -->
     *         <collection property="pets" column="id" ofType="Pet"
     *                     select="com.hspedu.mapper.PetMapper.getPetByUserId"/>
     *     </resultMap>
     *     <select id="getUserById" parameterType="Integer" resultMap="UserResultMap">
     *         SELECT * FROM `mybatis_user` WHERE `id` = #{id}
     *     </select>
     */

    @Select("SELECT * FROM `mybatis_user` WHERE `id` = #{id}")
    @Results({
          @Result(id = true, property = "id", column = "id"),
          @Result(property = "name", column = "name"),
          //这里请小伙伴注意，pets属性对应的是集合
          @Result(property = "pets",
                  column = "id",
                  many = @Many(select = "com.hspedu.mapper.PetMapperAnnotation.getPetByUserId"))
    })
    public User getUserById(Integer id);
}
