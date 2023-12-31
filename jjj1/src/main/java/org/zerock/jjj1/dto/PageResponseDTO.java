package org.zerock.jjj1.dto;


//todo
//현재페이지번호,
//사이즈
//시작페이지번호 => 페이지번호[]
//마지막페이지번호

import lombok.Data;

import java.util.List;

@Data
public class PageResponseDTO<E> {

    private List<E> dtoList;

    private long totalCount;

    private List<Integer> PageNums;

    private boolean prev, next;

    private PageRequestDTO requestDTO;

    public PageResponseDTO(List<E> dtoList, Long totalCount, PageRequestDTO pageRequestDTO){

        this.dtoList = dtoList;

        this.totalCount = totalCount;

        this.requestDTO = pageRequestDTO;

    }


}
