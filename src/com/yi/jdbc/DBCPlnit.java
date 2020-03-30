package com.yi.jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;



public class DBCPlnit extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}
	
	
	private void loadJDBCDriver() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}
	
	private void initConnectionPool() {
		try {
			//DB이름 잘 맞춰주기
		String url = "jdbc:mysql://localhost:3306/hhn_movie?"
				+"useUnicode=true&characterEncoding=utf8";
		String user = "root";
		String pass = "rootroot";
		
		//Commons DBCP, loggin, pool 라이브러리 설치 후 import도 commons 로 하기 
		ConnectionFactory connFactory = new DriverManagerConnectionFactory(url, user, pass);
		PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
		
		//커넥션을 검사할 때 사용할 쿼리를 설정한다.
		//종료된 커넥션을 dbcp의 connection pool에선 여전히 가지고 있는 상태일 때, DB 관련 프로그램이 호출되면 커넥션 관련 에러가 발생하게 된다.
		//자바에서 DB를 사용하기 전에 해당 connection이 정상적인지 검사 하도록 하는 것
		//select 1 -> MicroSoft SQL Server에서 권장하는 검사 방법 (select스페이스1)
		poolableConnFactory.setValidationQuery("select 1");
		
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		//검사 주기 (1초 * 60 * 5) = 5분 마다 검사 (일반적으로는 10분~30분 단위로 검사)
		poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);
		//유휴 커넥션 검사할 것인가
		//유효하지 않은 커넥션은 검사해서 연결을 끊어줌
		poolConfig.setTestWhileIdle(true);
		//사용할 수 있는 최소 커넥션 갯수
		//전체 커넥션 갯수
		poolConfig.setMinIdle(4);
		poolConfig.setMaxTotal(50);
		
		GenericObjectPool<PoolableConnection> connectionPool 
			= new GenericObjectPool<>(poolableConnFactory, poolConfig);
		poolableConnFactory.setPool(connectionPool);
		
		Class.forName("org.apache.commons.dbcp2.PoolingDriver");
		PoolingDriver driver = 
				(PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
		
		//jdbc:apache:commons:dbcp:chap14 
		//커넥션 풀 이름 (DB명이랑 맞추는게 편하다)
		driver.registerPool("hhn_movie", connectionPool);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	
}
