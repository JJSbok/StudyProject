package org.zerock.j1.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Name;
import lombok.*;

//모든 Entitiy는 프라이머리 값이 있어야함
//    Entity class
@Entity
@Table(name="tbl_sample")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Sample {

    @Id
    private String keyCol;

    private String first;

//Entity 객체
    private String last;


}
