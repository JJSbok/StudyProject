package org.zerock.j1.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.j1.domain.Sample;

import java.util.Optional;

@SpringBootTest
@Log4j2
public interface SampleRepository {

    @Autowired
    private SampleRepository sampleRepository;

//    @Test
//    public void test1(){
//        log.info("")
//    }

    @Test
    public void testInsert(){
        IntStream.rageClosed(1, 100).forEach(i -> {
           Sample obj = Sample.builder()
                   .keyCol("u"+i)
                   .first("first")
                   .last("last")
                   .build();

           sampleRepository.save(obj);
        });
    }

    @Test
    public void testRead(){
        String keyCol = "u10";
        Optional<Sample> result = sampleRepository.findById(keyCol);

        Sample obj = result.orElseThrow();
        log.info(obj);
    }

    @Test
    public void testDelete(){
        String keyCol = "u1";

        sampleRepository.deleteById(keyCol);
    }
}
