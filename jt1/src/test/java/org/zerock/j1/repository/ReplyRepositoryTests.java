package org.zerock.j1.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Board;
import org.zerock.j1.domain.Reply;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.service.ReplyService;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertOne(){

        Long bno = 100L;

        Board board = Board.builder().bno(bno).build();

        Reply reply = Reply.builder()
                .replyText("Reply.....1")
                .replyer("replyer00")
                .board(board)
                .build();

        replyRepository.save(reply);
    }

    @Test
    public void testInsertDummies(){

        Long[] bnoArr = {199L, 196L, 192L, 184L, 181L};

        for (Long bno : bnoArr){

            Board board = Board.builder().bno(bno).build();

            for(int i = 0; i< 50; i ++){

                Reply reply = Reply.builder()
                        .replyText("Reply..."+bno+"--"+i)
                        .replyer("replyer"+i)
                        .board(board)
                        .build();

                replyRepository.save(reply);
            }

        }//end for

    }

    @Test
    public void testListBoard(){

        Long bno = 99L;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listBoard(bno, pageable);


        //lazy fetch
        result.get().forEach(r -> log.info(r));

    }
    @Test
    public void testCount(){
        Long bno = 199L;

        long count = replyRepository.getCountBoard(bno);
        log.info("count : " + count);
    }

    @Test
    public void testListLast() {

        ReplyPageRequestDTO requestDTO = ReplyPageRequestDTO.builder().bno(199L).last(true).build();

        log.info(replyService.list(requestDTO));



    }
}