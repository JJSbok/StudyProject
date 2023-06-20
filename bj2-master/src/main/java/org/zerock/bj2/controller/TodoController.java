package org.zerock.bj2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.bj2.dto.PageRequestDTO;
import org.zerock.bj2.dto.PageResponseDTO;
import org.zerock.bj2.dto.TodoDTO;
import org.zerock.bj2.service.TodoService;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo/")
@Log4j2
public class TodoController {
    
    private final TodoService todoService;

       // list
       @GetMapping("list")
       public void list(PageRequestDTO pageRequestDTO, Model model){
           log.info("list");

           PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getList(pageRequestDTO);

           model.addAttribute("pageResponseDTO",pageResponseDTO);

       }

       @GetMapping("read/{tno}")
       public String read(
            @PathVariable("tno") long tno,
            PageRequestDTO pageRequestDTO,
            Model model){

        log.info("read..........");
        log.info(tno);

        TodoDTO todoDTO = TodoDTO.builder()
        .tno(tno)
        .title("Sample....")
        .writer("user00")
        .dueDate("2023-06-14")
        .build();

        model.addAttribute("todo", todoDTO);

        return "todo/read";
       }
   


}
