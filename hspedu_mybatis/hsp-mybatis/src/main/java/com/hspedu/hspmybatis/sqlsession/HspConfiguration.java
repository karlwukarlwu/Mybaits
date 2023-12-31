package com.hspedu.hspmybatis.sqlsession;

import com.hspedu.hspmybatis.config.Function;
import com.hspedu.hspmybatis.config.MapperBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author 韩顺平
 * @version 1.0
 * 读取xml文件, 建立连接
 */
public class HspConfiguration {

    //属性-类的加载器
    private static ClassLoader loader =
            ClassLoader.getSystemClassLoader();

    //读取xml文件信息，并处理
    public Connection build(String resource) {

        Connection connection = null;

        try {
            //加载配置hsp_mybatis.xml 获取到对应的InputStream
            InputStream stream =
                    loader.getResourceAsStream(resource);
            //解析hsp_mybatis.xml  => dom4j
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            //获取到hsp_mybatis.xml 的根元素 <database>
            Element root = document.getRootElement();
            System.out.println("root=" + root);
            //解析root元素，返回Connection => 老师准备单独的编写一个方法
            connection = evalDataSource(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //方法会解析hsp_config.xml 信息,并返回Connection
    //eval: 评估/解析
    private Connection evalDataSource(Element node) {
        if (!"database".equals(node.getName())) {
            throw new RuntimeException("root 节点应该是<database>");
        }
        //连接DB的必要参数
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;

        //遍历node下的子节点，获取属性值
        for (Object item : node.elements("property")) {
            Element i = (Element) item;//i 就是 对应property节点
            String name = i.attributeValue("name");
            String value = i.attributeValue("value");

            //判断是否得到name 和 value
            if (name == null || value == null) {
                throw new RuntimeException("property 节点没有设置name或者value属性");
            }
            switch (name) {
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "driverClassName":
                    driverClassName = value;
                    break;
                case "password":
                    password = value;
                    break;
                default:
                    throw new RuntimeException("属性名没有匹配到...");
            }
        }

        Connection connection = null;

        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection; //返回Connection
    }

    //读取XxxMapper.xml，能够创建MapperBean对象
    //path 就是xml的路径+文件名  是从类的加载路径计算的
    //老师说明：如果XxxMapper.xml 文件是防在resources目录下，直接传入xml文件名即可

    public MapperBean readMapper(String path) {

        MapperBean mapperBean = new MapperBean();

        try {
            //获取到xml文件对应的InputStream
            InputStream stream = loader.getResourceAsStream(path);
            SAXReader reader = new SAXReader();
            //获取到xml文件对应的document-dom4j
            Document document = reader.read(stream);
            //得到xml文件的根元素/根节点
            /*
            <mapper namespace="com.hspedu.mapper.MonsterMapper">
                <!--实现配置接口方法getMonsterById-->
                <select id="getMonsterById" resultType="com.hspedu.entity.Monster">
                    select * from monster where id = ?
                </select>


            </mapper>
             */
            Element root = document.getRootElement();

            //获取到namespace
            String namespace = root.attributeValue("namespace").trim();
            //设置mapperBean的属性interfaceName
            mapperBean.setInterfaceName(namespace);
            //得到root的迭代器-可以遍历它的子节点/子元素-生成Function
            Iterator rootIterator = root.elementIterator();
            //保存接口下所有的方法信息
            List<Function> list = new ArrayList<>();
            //遍历它的子节点/子元素-生成Function
            while (rootIterator.hasNext()) {
                //取出一个子元素- dom4j Element
                /**
                 * <select id="getMonsterById" resultType="com.hspedu.entity.Monster">
                 *                     select * from monster where id = ?
                 *  </select>
                 */
                Element e = (Element) rootIterator.next();
                Function function = new Function();
                String sqlType = e.getName().trim();
                String funcName = e.attributeValue("id").trim();
                //resultType是返回类型的全路径-即全类名
                String resultType = e.attributeValue("resultType").trim();
                String sql = e.getText().trim();
                //开始封装
                function.setSql(sql);
                function.setFuncName(funcName);
                function.setSqlType(sqlType);
                //这里老师多说一句 function-private Object resultType; 是resultType实例
                //老师使用反射生成一个对象, setResultType

                Object newInstance = Class.forName(resultType).newInstance();
                function.setResultType(newInstance);

                //将封装好的function对象加入到 list
                list.add(function);

            }
            //while循环结束后, 将function的list设置
            mapperBean.setFunctions(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapperBean;
    }

}
