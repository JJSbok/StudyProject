package org.zerock.j1.dto;

import lombok.*;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyPageRequestDTO extends PageRequestDTO{

    private Long bno;
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size= 50;

    private boolean last;



}
