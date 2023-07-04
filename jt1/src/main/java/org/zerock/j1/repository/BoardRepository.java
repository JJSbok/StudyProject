package org.zerock.j1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.j1.domain.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findByTitleContaining(String title);

    @Query("select b from Board b where b.title = :title")
    List<Board> listTitle(String title);
    Page<Board> findByContentsContaining(String content, Pageable pageable);



}
