package org.mymybatis.config;

import java.util.List;

/**
 * Karl Rules!
 * 2023/11/17
 * now File Encoding is UTF-8
 * 将mapper信息进行封装
 *   已经封装了xml的方法 这里以属性调用
 */
public class MapperBean {
    private String interfaceName;//接口名

    //接口下的所有方法-集合
    private List<Function> functions;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    @Override
    public String toString() {
        return "MapperBean{" +
                "interfaceName='" + interfaceName + '\'' +
                ", functions=" + functions +
                '}';
    }


}
