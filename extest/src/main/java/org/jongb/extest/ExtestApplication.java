package org.jongb.extest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"org.jongb.extest.**.mappers"})
public class ExtestApplication {


    public static void main(String[] args) {
SpringApplication.run(ExtestApplication.class, args);
    }
}
