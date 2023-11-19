package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 * MonsterAnnotation: 使用注解的方式来配置接口方法
 */
public interface MonsterAnnotation {

    //添加monster
    /*

           老师解读
           1. 使用注解方式配置接口方法addMonster
           2. 回顾xml如何配置
           <insert id="addMonster" parameterType="Monster" useGeneratedKeys="true" keyProperty="id">
                INSERT INTO `monster`
                (`age`, `birthday`, `email`, `gender`, `name`, `salary`)
                VALUES (#{age}, #{birthday}, #{email}, #{gender}, #{name}, #{salary})
            </insert>

            老师解读
            1. useGeneratedKeys = true 返回自增的值
            2. keyProperty = "id" 自增值对应的对象属性
            3. keyColumn = "id" 自增值对应的表的字段


     */
    @Insert("INSERT INTO `monster` (`age`, `birthday`, `email`, `gender`, `name`, `salary`) " +
            "VALUES (#{age}, #{birthday}, #{email}, #{gender}, #{name}, #{salary})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void addMonster(Monster monster);

    //根据id删除一个Monster
    /*
        xml文件中的配置
        <delete id="delMonster" parameterType="Integer">
            DELETE FROM `monster` WHERE id = #{id}
        </delete>
     */
    @Delete("DELETE FROM `monster` WHERE id = #{id}")
    public void delMonster(Integer id);

    //修改Monster
    /*
         <update id="updateMonster" parameterType="Monster">
            UPDATE `monster`
            SET `age`=#{age} , `birthday`= #{birthday}, `email` = #{email},
            `gender` = #{gender} , `name`= #{name}, `salary` = #{salary}
            WHERE id = #{id}
        </update>
     */
    @Update("UPDATE `monster` " +
            "SET `age`=#{age} , `birthday`= #{birthday}, `email` = #{email}, " +
            "`gender` = #{gender} , `name`= #{name}, `salary` = #{salary} " +
            "WHERE id = #{id}")
    public void updateMonster(Monster monster);

    //查询-根据id
    /*
        xml配置
        <select id="getMonsterById" resultType="Monster">
            SELECT * FROM `monster` WHERE id = #{id}
        </select>
     */
    @Select("SELECT * FROM `monster` WHERE id = #{id}")
    public Monster getMonsterById(Integer id);

    //查询所有的Monster
    /*
        xml配置
        <select id="findAllMonster" resultType="Monster">
            SELECT * FROM `monster`
        </select>
     */
    @Select("SELECT * FROM `monster`")
    public List<Monster> findAllMonster();
}
