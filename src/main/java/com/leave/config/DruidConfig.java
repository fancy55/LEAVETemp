package com.leave.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//阿里的 Druid 数据库连接池，MPP 架构设计
/**
 * 1、处理的数据量规模较大。
 * 2、可以进行数据的实时查询展示。
 * 3、它的查询模式是交互式的，这也说明其查询并发能力有限。
 * */

/**
 * 预聚合：可以减少数据的存储以及避免查询时很多不必要的计算
 * 列式存储
 * 字典编码
 * 位图索引
 */
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DataSource druid(){
        DataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    //配置Druid的监控
    //1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>(16);

        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        //默认允许所有访问
        initParams.put("allow", "");//默认所有/localhost
        //initParams.put("deny","192.168.15.21");//拒绝访问

        bean.setInitParameters(initParams);
        return bean;
    }

    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>(16);
        initParams.put("exclusions", "*.js,*.css,/druid/*");//拦截排除静态资源的请求

        bean.setInitParameters(initParams);

        bean.setUrlPatterns(Collections.singletonList("/*"));

        return bean;
    }

//    //拦截druid下所有请求
//    @Bean
//    public ServletRegistrationBean druidServlet(){
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        //ip白名单
//        servletRegistrationBean.addUrlMappings("allow","127.0.0.1");
//        //ip黑名单
////        servletRegistrationBean.addUrlMappings("deny","127.0.0.1");
//        //控制台用户
//        servletRegistrationBean.addInitParameter("loginUsername","qly");
//        servletRegistrationBean.addInitParameter("loginPassword","321");
//        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
////        servletRegistrationBean.addInitParameter("resetEnable", "false");
//        return servletRegistrationBean;
//    }
//
//    //配置一个web监控的filter
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        //拦截排除静态资源的请求
//        filterRegistrationBean.addInitParameter("exclusions","*.js,*.jpg,*.png,*.css,*.ico,*.gif,/druid/*");
//        return filterRegistrationBean;
//    }

//    @ConfigurationProperties(prefix = "spring.datasource.druid")
//    @Component
//    @Data
//    public class DataSourceProperties{
//        private String url;
//        private String username;
//        private String password;
//        private String driverClassName;
//        private int initialSize;
//        private int minIdle;
//        private int maxActive;
//        private int maxWait;
//        private int timeBetweenEvictionRunsMillis;
//        private int minEvictableIdleTimeMillis;
//        private String validationQuery;
//        private boolean testWhileIdle;
//        private boolean testOnBorrow;
//        private boolean testOnReturn;
//        private boolean poolPreparedStatements;
//        private int maxPoolPreparedStatementPerConnectionSize;
//        private String filters;
//        private String connectionProperties;
//    }
}
