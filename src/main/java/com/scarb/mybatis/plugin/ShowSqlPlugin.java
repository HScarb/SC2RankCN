package com.scarb.mybatis.plugin;

import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.mysql.jdbc.JDBC4PreparedStatement;



@Intercepts({@Signature(
        type= ParameterHandler.class,
        method = "setParameters",
        args = {PreparedStatement.class})})

public class ShowSqlPlugin implements Interceptor{

    public Object intercept(Invocation invocation) throws Throwable {

        if(invocation!=null && invocation.getArgs()!=null && invocation.getArgs().length > 0 ){
            if(invocation.getArgs()[0] instanceof PreparedStatement){
                PreparedStatement st = (PreparedStatement)invocation.getArgs()[0];
                System.out.println("执行的SQL为:" + st);
            }
        }
        return invocation.proceed();
    }

    public Object plugin(Object target) {


        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }

}