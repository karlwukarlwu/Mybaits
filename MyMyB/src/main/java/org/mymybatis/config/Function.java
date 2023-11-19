package org.mymybatis.config;

/**
 * Karl Rules!
 * 2023/11/17
 * now File Encoding is UTF-8
 */
public class Function {
//    我们的xml读取过程中 会生成什么？ 拿属性开始记录
    private String sqlType;//SQL的类型（增删改查）
    private String funcName; //方法名
    private String sql;//执行的sql语句
    private Object resultType;//返回类型
    private String parameterType;//参数类型

    public Function() {
    }
    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object getResultType() {
        return resultType;
    }

    public void setResultType(Object resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    @Override
    public String toString() {
        return "Function{" +
                "sqlType='" + sqlType + '\'' +
                ", funcName='" + funcName + '\'' +
                ", sql='" + sql + '\'' +
                ", resultType=" + resultType +
                ", parameterType='" + parameterType + '\'' +
                '}';
    }
}
