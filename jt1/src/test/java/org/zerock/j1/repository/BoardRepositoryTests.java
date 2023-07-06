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
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;

import java.util.Arrays;
import java.util.List;
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

    @Test
    public void testQuery1_1(){


        java.util.List<Board> list = boardRepository.listTitle("1");

        log.info("---------------------------------------------");
        log.info(list.size());
        log.info(list);
    }

    @Test
    public void testQuery1_2(){


        java.util.List<Object[]> list = boardRepository.listTitle2("1");

        log.info("---------------------------------------------");
        log.info(list.size());
        log.info(list);

        list.forEach(arr -> log.info(Arrays.toString(arr)));
    }

    @Test
    public void testQuery1_3(){

    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.listTitle3("1", pageable);

        log.info("---------------------------------------------");
        log.info(result);
    }

    @Transactional
    @Test
    @Commit
    public void testModify(){
        Long bno = 100L;
        String title = "Modified Title 100";

        int count = boardRepository.modifyTitle(title, bno);

        log.info("---------------------");
        log.info(count);
    }

    @Test
    public void testNative(){
       List<Object[]> result =  boardRepository.listNative();

       result.forEach(arr -> log.info(Arrays.toString(arr)));

    }

    @Test
    public void testSearch1(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.search1(null,"1",pageable);

        log.info(result.getTotalElements());

        result.get().forEach(b -> log.info(b));
    }

    @Test
    public void testListWithRcnt(){
        List<Object[]> result = boardRepository.getListWithRcnt();

        for (Object[] result2 : result) log.info(Arrays.toString(result2));
    }

    @Test
    public void testListWithRcntSearch(){

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        boardRepository.searchWithRcnt("tcw","1",pageable);

    }

    @Test
    public void test0706_1(){

        PageRequestDTO pageRequest = new PageRequestDTO();

        PageResponseDTO<BoardListRcntDTO> resonseDTO =
                boardRepository.searchDTORcnt(pageRequest);

        log.info(resonseDTO);
    }
}
