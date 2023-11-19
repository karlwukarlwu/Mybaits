package org.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author 韩顺平
 * @version 1.0
 * Monster 和 monster表有映射关系
 *
 * 老师解读
 * @Getter 就会给所有属性 生成对应的getter
 * @Setter 就会给所有属性 生成对应的setter
 * @ToString 生成 toString...
 * @NoArgsConstructor 生成无参构造器
 * @AllArgsConstructor 生成要给全参构造器
 * @Data 注解 会生成 getter, setter, toString, 无参构造器, 全参构造器
 *
 * 当我们点开target目录 编译生成的.class 文件,
 * 会发现这些注解帮我们生成了getter, setter, toString, 无参构造器, 全参构造器
 */
//这些便捷来自于lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Monster {

    private Integer id;
    private Integer age;
    private String name;
    private String email;
    private Date birthday;
    private double salary;
    private Integer gender;

}
