package com.leave;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//spring session是一个解决集群环境中，session持久化管理的依赖库。spring session会采用redis作为session的持久化方式。

//@EnableCaching
//优化
//@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFERRED)
//@EnableAsync
//@EnableTransactionManagement
@SpringBootApplication
@MapperScan({"com.leave.mapper","com.leave.mapper.imp"})
public class LeaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaveApplication.class, args);
    }

}
