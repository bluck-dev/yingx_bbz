package com.bbz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@tk.mybatis.spring.annotation.MapperScan("com.bbz.dao")
@MapperScan("com.bbz.dao")
@SpringBootApplication
public class YingxBbzApplication {

    public static void main(String[] args) {
        SpringApplication.run(YingxBbzApplication.class, args);
    }

}
