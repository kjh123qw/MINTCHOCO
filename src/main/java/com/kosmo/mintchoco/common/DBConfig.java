package com.kosmo.mintchoco.common;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@mintchoco.cd32mfmgi3hk.ap-northeast-2.rds.amazonaws.com:1521:DB");
		dataSource.setUsername("admin");
		dataSource.setPassword("admin1234");
		return dataSource;
	}
}
