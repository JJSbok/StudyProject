package org.zerock.jjj1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.jjj1.domain.Sample;

//mybatis mapper랑 비슷함
public interface SampleRepository extends JpaRepository<Sample, String> {


}
