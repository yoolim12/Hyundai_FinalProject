package com.hyundai.project.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value = "com.hyundai.project.memberDAO", sqlSessionFactoryRef = "memberSqlSessionFactory")
@EnableTransactionManagement
public class MemberDatabaseConfig {

	@Bean(name = "memberDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.member")
	public DataSource memberDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "memberSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("memberDataSource") DataSource memberDataSource,
			ApplicationContext applicationContext) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(memberDataSource);
		sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mapper/member/*.xml"));
		return sessionFactory.getObject();
	}

	@Bean(name = "memberSqlSession")
	public SqlSessionTemplate memberSqlTemplate(SqlSessionFactory memberSqlSessionFactory) {

		return new SqlSessionTemplate(memberSqlSessionFactory);
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(memberDataSource());
	}
}