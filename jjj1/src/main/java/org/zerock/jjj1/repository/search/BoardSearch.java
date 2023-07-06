package org.zerock.jjj1.repository.search;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.jjj1.dto.BoardListRcntDTO;
import org.zerock.jjj1.dto.PageRequestDTO;
import org.zerock.jjj1.dto.PageResponseDTO;

import static org.springframework.data.support.PageableExecutionUtils.getPage;

public interface BoardSearch {
    PageResponseDTO<BoardListRcntDTO> searchDTORcnt(PageRequestDTO requestDTO);

    default Pageable makePageable(PageRequestDTO requestDTO){

        Pageable pageable = PageRequest.of(requestDTO.getPage()-1 , requestDTO.getSize(), Sort.by("bno").descending());

        return pageable;
    }
}
