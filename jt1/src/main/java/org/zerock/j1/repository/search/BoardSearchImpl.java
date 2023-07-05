package org.zerock.j1.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j1.domain.Board;
import org.zerock.j1.domain.QBoard;
import org.zerock.j1.domain.QReply;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(String searchType, String keyWord, Pageable pageable) {

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        if (keyWord != null && searchType != null) {
            //tc -> [t,c]
            String[] searchArr = searchType.split("");

            BooleanBuilder searchBulider = new BooleanBuilder();

            for (String type : searchArr)

                switch (type) {
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

    @Override
    public Page<Object[]> searchWithRcnt(String searchType, String keyWord, Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));


        if (keyWord != null && searchType != null) {
            //tc -> [t,c]
            String[] searchArr = searchType.split("");

            BooleanBuilder searchBulider = new BooleanBuilder();

            for (String type : searchArr)

                switch (type) {
                    case "t" -> searchBulider.or(board.title.contains(keyWord));
                    case "c" -> searchBulider.or(board.contents.contains(keyWord));
                    case "w" -> searchBulider.or(board.writer.contains(keyWord));
                }
            query.where(searchBulider);

        }


        query.groupBy(board);

        JPQLQuery<Tuple> tupleQuery =
                query.select(board.bno, board.title, board.writer, reply.countDistinct());

        this.getQuerydsl().applyPagination(pageable, tupleQuery);

        log.info("11111111");
        List<Tuple> tuples = tupleQuery.fetch();

        List<Object[]> arrList = tuples.stream().map(tuple -> tuple.toArray()).collect(Collectors.toList());

        log.info("22222222");
        log.info(tuples);
        log.info("33333333");
        long count = tupleQuery.fetchCount();

        log.info("count: " + count);

        return new PageImpl<>(arrList, pageable, count);
    }
}
