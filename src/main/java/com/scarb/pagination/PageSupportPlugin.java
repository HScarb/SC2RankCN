package com.scarb.pagination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.scarb.util.ReflectUtil;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageSupportPlugin implements Interceptor {

    private static final Logger LOG = LoggerFactory.getLogger(PageSupportPlugin.class);

    private String databaseType;

    private Page getPageInfo(Object obj){
        return (Page)ReflectUtil.getFieldValue(obj,"page");
    }

    private String getMysqlPageSql(Object obj, StringBuffer sqlBuffer) {

        int offset = (getPageInfo(obj).getPageNo() - 1) * getPageInfo(obj).getPageSize();
        sqlBuffer.append(" limit ").append(offset).append(",").append(getPageInfo(obj).getPageSize());
        return sqlBuffer.toString();
    }

    private String getOraclePageSql(Object obj, StringBuffer sqlBuffer) {
        int offset = (getPageInfo(obj).getPageNo() - 1) * getPageInfo(obj).getPageSize() + 1;
        sqlBuffer.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + getPageInfo(obj).getPageSize());
        sqlBuffer.insert(0, "select * from (").append(") where r >= ").append(offset);
        return sqlBuffer.toString();
    }

    public void setTotalRecord(Object obj, MappedStatement mappedStatement, Connection connection) {

        BoundSql boundSql = mappedStatement.getBoundSql(obj);
        String sql = boundSql.getSql();

        String countSql = this.getCountSql(sql,getPageInfo(obj).isCheckFirstFrom());

        LOG.info("Count TotalNumber SQL:[" + countSql + "]");
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        for(int i=0;i<parameterMappings.size();i++){
            if("sortColumn".equalsIgnoreCase(((ParameterMapping)parameterMappings.get(i)).getProperty())){
                parameterMappings.remove(i);
            }
        }

        BoundSql countBoundSql = new BoundSql(
                mappedStatement.getConfiguration(), countSql,
                parameterMappings, obj);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, obj, countBoundSql);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);

            parameterHandler.setParameters(pstmt);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalRecord = rs.getInt(1);
                getPageInfo(obj).setTotalRecord(totalRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String getCountSql(String sql, boolean checkFirstFrom) {
        if(checkFirstFrom){
            int index =  sql.indexOf("from");
            int orderIndex = sql.indexOf("order by");
            if(orderIndex > 0) {
                return "select count(*) " + sql.substring(index,orderIndex);
            }else{
                return "select count(*) " + sql.substring(index);
            }
        }else{
            return "select count(*) from ( " + sql + " ) _tempCount4PaginationPlugin";
        }

    }

    private String getPageSql(Object obj, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        if ("mysql".equalsIgnoreCase(databaseType)) {
            return getMysqlPageSql(obj, sqlBuffer);
        } else if ("oracle".equalsIgnoreCase(databaseType)) {
            return getOraclePageSql(obj, sqlBuffer);
        }
        return sqlBuffer.toString();
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");

        BoundSql boundSql = delegate.getBoundSql();
        Object obj = boundSql.getParameterObject();
        if (ReflectUtil.getFieldValue(obj, "page") != null) {
            MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");
            Connection connection = (Connection)invocation.getArgs()[0];
            String sql = boundSql.getSql();
            this.setTotalRecord(obj,mappedStatement, connection);

            if(getPageInfo(obj).getTotalRecord() == 0){ //查询结果为空，无须再执行
                return invocation.proceed();
            }

            String pageSql = this.getPageSql(obj, sql);
            LOG.info("Pagenation SQL:[" + pageSql + "]");
            ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object invocation) {
        return Plugin.wrap(invocation, this);
    }

    @Override
    public void setProperties(Properties prop) {
        this.databaseType = prop.getProperty("databaseType");
    }

}