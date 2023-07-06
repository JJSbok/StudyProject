package org.zerock.jjj1.dto;

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
    private int replyCount;



}
