package org.zerock.j1.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.j1.domain.Board;

import java.util.List;

public interface BoardSearch {

    Page<Board> search1(String searchType, String keyWord, Pageable pageable);

    Page<Object[]> searchWithRcnt(String searchType, String keyWord, Pageable pageable);
}
