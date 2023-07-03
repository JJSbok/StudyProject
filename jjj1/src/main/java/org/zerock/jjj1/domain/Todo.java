package org.zerock.jjj1.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "tbl_todo2")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(length = 300, nullable = false)
    private String title;



}
