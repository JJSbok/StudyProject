package org.zerock.jjj1.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.jjj1.domain.Sample;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class SampleRepositoryTest {

    @Autowired
    private SampleRepository sampleRepository;

//    @Test
//    public void test1(){
//        log.info("")
//    }

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
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

    @Test
    public void testPaging(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("keyCol").descending());

       Page<Sample> result = sampleRepository.findAll(pageable);

       log.info(result.getTotalElements());
        log.info(result.getTotalPages());

        result.getContent().forEach(obj -> log.info(obj));

    }
}
