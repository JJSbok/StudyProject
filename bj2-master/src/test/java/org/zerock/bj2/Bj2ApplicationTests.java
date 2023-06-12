package org.zerock.bj2;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.bj2.mappers.TimeMapper;

import java.sql.Connection;

import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class Bj2ApplicationTests {

	@Autowired
	DataSource dataSource;

	@Autowired
	TimeMapper timeMapper;

	@Test
	public void testTime(){

		log.info("-----------------------------");
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());
	}

	@Test
	void contextLoads() {
		log.info("Context Load....");
	}

	@Test  
	public void testConnection(){

		try(Connection conn = dataSource.getConnection()){
			log.info(conn);
		} catch(Exception e){
			e.printStackTrace();

		} 
	}

}
