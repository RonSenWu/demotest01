package com.wrs.cems;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;

@SpringBootApplication
@Configuration
@MapperScan("com.wrs.cems.mapper")
public class CemsApplication{

    public static void main(String[] args) {
        SpringApplication.run(CemsApplication.class, args);
    }

}
