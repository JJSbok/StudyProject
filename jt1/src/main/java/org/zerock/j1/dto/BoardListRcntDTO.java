package org.zerock.j1.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardListRcntDTO {

    private Long bno;
    private String title;
    private String writer;
    private Long replyCount;


}
