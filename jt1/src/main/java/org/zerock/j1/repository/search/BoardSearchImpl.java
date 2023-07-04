package org.zerock.j1.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j1.domain.Board;
import org.zerock.j1.domain.QBoard;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
    public Page<Board> search1(String searchType, String keyWord, Pageable pageable) {

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        if(keyWord != null && searchType != null) {
           //tc -> [t,c]
          String[] searchArr = searchType.split("");

            BooleanBuilder searchBulider = new BooleanBuilder();

            for ( String type: searchArr)

                switch(type){
                case "t" -> searchBulider.or(board.title.contains(keyWord));
                case "c" -> searchBulider.or(board.contents.contains(keyWord));
                case "w" -> searchBulider.or(board.writer.contains(keyWord));
                }
            query.where(searchBulider);

        }

//        query.where(board.title.contains("1"));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        log.info(list);

        log.info("count : " + count);

        return new PageImpl<>(list, pageable, count);
    }
}
