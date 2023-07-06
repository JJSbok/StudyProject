package org.zerock.j1.dto;


//todo
//현재페이지번호,
//사이즈
//시작페이지번호 => 페이지번호[]
//마지막페이지번호

import lombok.Data;

import java.util.List;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<E> {

    private List<E> dtoList;

    private long totalCount;

    private List<Integer> pageNums;

    private boolean prev, next;

    private PageRequestDTO requestDTO;

    private int page, size ,start, end;


    public PageResponseDTO(List<E> dtoList, Long totalCount, PageRequestDTO pageRequestDTO) {

        this.dtoList = dtoList;
        this.totalCount = totalCount;
        this.requestDTO = pageRequestDTO;

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();


        //pageNum 13 >> 1.3 >> 2.0
        int tempEnd = (int) (Math.ceil(page/10.0) * 10);

        this.start = tempEnd - 9;
        this.prev = this.start != 1;

        //20 178 >> 17.8 >>
        int realEnd = (int) (Math.ceil(totalCount/(double) size));

        this.end = tempEnd > realEnd ? realEnd : tempEnd;

        this.next = (this.end * this.size) < totalCount;

        this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
    }


}
