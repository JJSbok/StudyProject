package org.zerock.jjj1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.jjj1.domain.Todo;
import org.zerock.jjj1.dto.PageResponseDTO;
import org.zerock.jjj1.dto.TodoDTO;
import org.zerock.jjj1.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<TodoDTO> getList() {

        Pageable pageable =
                PageRequest.of(0,20, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        List<TodoDTO> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());

//        PageResponseDTO<TodoDTO> response = new PageResponseDTO<>();
//        response.setDtZoList(dtoList);
//        return response;
        return null;
    }

    @Override
    public TodoDTO register(TodoDTO dto) {

        Todo entity = modelMapper.map(dto, Todo.class);

        Todo result = todoRepository.save(entity);

        return modelMapper.map(result, TodoDTO.class);
    }
}