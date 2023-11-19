import org.apache.ibatis.session.SqlSession;
import org.entity.Monster;
import org.junit.Before;
import org.junit.Test;
import org.mapper.MonsterMapper;
import org.util.MyBatisUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Karl Rules!
 * 2023/11/16
 * now File Encoding is UTF-8
 */
public class MonsterMapperTest {
    private SqlSession sqlSession;
    //  我们自己写的接口
    private MonsterMapper monsterMapper;

    //  当我们用了before注解 意味着这个方法会在每个测试方法前执行
    @Before
    public void init() {
        sqlSession = MyBatisUtils.getSqlSession();
//       拿到MonsterMapper的代理对象
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
//        monsterMapper=class com.sun.proxy.$Proxy7
        System.out.println("monsterMapper=" + monsterMapper.getClass());
    }

    @Test
    public void findMonsterByNameORId() {
        Monster monster = new Monster();
        monster.setId(8);
        monster.setName("狐狸精-100");
        List<Monster> monsters =
                monsterMapper.findMonsterByNameORId(monster);

        for (Monster m : monsters) {
            System.out.println("m-" + m);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("操作成功~");
    }

//   模糊查询
    @Test
    public void findMonsterByName() {

        List<Monster> monsters = monsterMapper.findMonsterByName("牛魔王");
        for (Monster monster : monsters) {
            System.out.println("monster--" + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("操作成功~");

    }

//    通过hashmap传递参数
    @Test
    public void findMonsterByIdAndSalary_PrameterHashMap() {
        //Diamond types are not supported at language level '5'
        //如何解决.=> 在pom.xml文件中指定编译器版本
        /*
             <!--指定Maven编译器 和 jdk版本-->
            <properties>
                <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
                <java.version>1.8</java.version>
            </properties>
         */

        Map<String, Object> map = new HashMap<>();
        map.put("id", 7);
        map.put("salary", 40);
        List<Monster> monsters =
                monsterMapper.findMonsterByIdAndSalary_PrameterHashMap(map);

        for (Monster monster : monsters) {
            System.out.println("monster--" + monster);
        }

        if (sqlSession != null) {
            sqlSession.close();
        }

        System.out.println("操作成功~");

    }
//    当传入参数和返回参数都是hashmap时
@Test
public void findMonsterByIdAndSalary_PrameterHashMap_ReturnHashMap() {

    Map<String, Object> map = new HashMap<>();
    map.put("id", 5);
    map.put("salary", 40);
    List<Map<String, Object>> monsterList =
            monsterMapper.findMonsterByIdAndSalary_PrameterHashMap_ReturnHashMap(map);

    //取出返回的结果-以map取出
    //回顾java基础,map遍历
    for (Map<String, Object> monsterMap : monsterList) {
        System.out.println("monsterMap-" + monsterMap);
//        {birthday=2023-11-16, gender=1, name=elephant-0, id=6, salary=1000.0, age=10, email=kate@qq.com}
//        每一个map的key是列名，value是对应的值

        //遍历monsterMap(方式1) ,取出属性和对应值
        //Set<String> keys = monsterMap.keySet();
        //for (String key : keys) {
        //    System.out.println(key + "=>" + monsterMap.get(key));
        //}

        //遍历monsterMap(方式2) ,取出属性和对应值
        for (Map.Entry<String, Object> entry : monsterMap.entrySet()) {
            System.out.println(entry.getKey() + "==>" + entry.getValue());
        }
        System.out.println("------------------------");
    }

    if (sqlSession != null) {
        sqlSession.close();
    }

    System.out.println("操作成功~");

}
}
