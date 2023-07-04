package org.zerock.j1.repository;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.j1.domain.Board;

import java.util.Optional;



@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert(){

        for(int i = 0 ; i < 100; i ++) {


            Board board = Board.builder()
                    .title("Sample Title" + i)
                    .contents("Sample Content" + i)
                    .writer("user00" + (i %10))
                    .build();

            boardRepository.save(board);
        }
    }

    @Test
    public void testRead() {

        Long bno = 1L;
        Optional<Board> result = boardRepository.findById(1L);


        log.info("------------------------------------------");

        Board board = result.orElseThrow();

        log.info(board);

    }

    @Test
    public void testUpdate(){

        Long bno = 1L;

        Optional<Board> result = boardRepository.findById(1L);

        Board board = result.orElseThrow();

        board.changeTitle("Update Title");

        boardRepository.save(board);

    }

    @Test
    public void testQuery1(){


        java.util.List<Board> list = boardRepository.findByTitleContaining("1");

        log.info("---------------------------------------------");
        log.info(list.size());
        log.info(list);
    }

    @Test
    public void testQuery2(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findByContentsContaining("1", pageable);

        log.info("---------------------------------------------");
        log.info(result);

    }
}
