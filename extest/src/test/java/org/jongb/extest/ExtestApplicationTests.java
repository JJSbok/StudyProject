package org.jongb.extest;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@MapperScan(basePackages = {"org.jongb.extest.**.mappers"})
class ExtestApplicationTests {

    public static void main(String[] args) {
        SpringApplication.run(ExtestApplicationTests.class, args);
    }


}
