package com.jin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控 : web.xml ServletRegistrationBean
    //因为SpringBoot内置了servlet容器，所以没有web.xml，替代方法: ServletRegistrationBean
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要有人登录，账号密码配置
        HashMap<String, String> initParams = new HashMap<>();
        //增加配置
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");

        //后台允许谁可以访问
        //initParams.put("allow", "localhost")：表示只有本机可以访问
        //initParams.put("allow", "")：为空或者为null时，表示允许所有访问
        initParams.put("allow", "");
        //deny：Druid 后台拒绝谁访问
        //initParams.put("deny", "192.168.1.20");表示禁止此ip访问

        bean.setInitParameters(initParams);//设置初始化参数
        return bean;
    }

    // filter
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //可以过滤哪些请求呢
        Map<String, String> initParams = new HashMap<>();
        //这些东西不进行统计～
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);
        return bean;
    }
}
