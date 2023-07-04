package org.zerock.jjj1.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.jjj1.domain.Todo;
import org.zerock.jjj1.dto.PageResponseDTO;
import org.zerock.jjj1.dto.TodoDTO;

@Transactional
public interface TodoService {

    PageResponseDTO<TodoDTO> getList();

    TodoDTO register(TodoDTO dto);
}
