package org.zerock.jjj1.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.jjj1.domain.Todo;
import org.zerock.jjj1.dto.TodoDTO;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testRead(){

        Long tno = 100L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo entity = result.orElseThrow();

        log.info("ENTITY............");
        log.info(entity);

        TodoDTO dto = modelMapper.map(entity,TodoDTO.class);

        log.info(dto);
    }

    @Test
    public void testInsert(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Todo todo = Todo.builder()
                    .title("Title" + i)
                    .build();
            Todo result = todoRepository.save(todo);

            log.info(result);
        });

    }

    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(1,10, Sort.by("tno").descending());

        Page<Todo> result =  todoRepository.findAll(pageable);

        log.info(result);
    }
}
