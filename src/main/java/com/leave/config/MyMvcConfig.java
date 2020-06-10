package com.leave.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class MyMvcConfig extends WebSecurityConfigurerAdapter {
    //解决 因为spring-security导致了403，post请求会被拦截
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略匹配项
        web.ignoring().antMatchers("/", "/user/**");
        //开启了CSRF保护，关闭即可，在configure(HttpSecurity http)方法中追加http.csrf().disable();
        super.configure(web);
    }
}
