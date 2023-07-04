package org.zerock.j1.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "t_board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String contents;

    @Column(length = 50, nullable = false)
    private String writer;

    public void changeTitle(String title){
        this.title = title;
    }
}
