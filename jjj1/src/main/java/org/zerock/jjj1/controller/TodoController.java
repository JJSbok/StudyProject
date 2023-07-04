package org.zerock.jjj1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.jjj1.dto.PageResponseDTO;
import org.zerock.jjj1.dto.TodoDTO;
import org.zerock.jjj1.service.TodoService;

@RequestMapping("/api/todos")
@RestController
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list() {
        return todoService.getList();
    }

    @PostMapping("/")
    public TodoDTO register(@RequestBody TodoDTO todoDTO){
        log.info("register..................");
        log.info(todoDTO);

        return todoService.register(todoDTO);
    }

}
