package org.zerock.j1.controller;

import org.springframework.web.bind.annotation.*;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;
import org.zerock.j1.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@CrossOrigin
@Log4j2
public class TodoController {
  
  private final TodoService todoService;

  @GetMapping("/list")
  public PageResponseDTO<TodoDTO> list(){
    
    return todoService.getList();
    
  }
@GetMapping("/{tno}")
public TodoDTO get(@PathVariable Long tno){

    return todoService.getOne(tno);
}



  @PostMapping("/")
  public TodoDTO register( @RequestBody TodoDTO todoDTO){

    log.info("register.....................");
    log.info(todoDTO);

    return todoService.register(todoDTO);
  }

  @DeleteMapping("/{tno}")
  public Map<String,String> delete(@PathVariable("tno")Long tno){

    todoService.remove(tno);

    return Map.of("result", "success");
  }

  @PutMapping("/{tno}")
  public Map<String, String> update(@PathVariable("tno")Long tno,
                                    @RequestBody TodoDTO todoDTO){

    todoDTO.setTno(tno);
    todoService.modify(todoDTO);

    return Map.of("result", "success");

  }

}
