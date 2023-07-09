package org.zerock.j1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zerock.j1.dto.BoardDTO;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.service.BoardService;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value ="/list")
    public PageResponseDTO<BoardListRcntDTO> list(@ParameterObject PageRequestDTO requestDTO){

        log.info(requestDTO);

        return boardService.listRcnt(requestDTO);
    }

    @GetMapping("/{bno}")

    public BoardDTO get(@PathVariable("bno")Long bno){

    return boardService.getOne(bno);
    }

}

