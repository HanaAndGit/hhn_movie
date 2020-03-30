package com.yi.dao.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.yi.dao.MovieDAO;
import com.yi.jdbc.DBCPlnit;
import com.yi.jdbc.JDBCUtil;
import com.yi.model.Movie;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieDAOTest {
	Connection conn = null;
	MovieDAO dao = null;
	
	//connection open
	@Before
	public void before() {
		try {
			DBCPlnit dbcpInit = new DBCPlnit();
			dbcpInit.init(); //JUnit 테스트는 서버가 돌지 않으므로, 직접 connection pool을 만들어줌 
			//원래는 xml 파일에서 자동으로 DBCPInit 클래스를 실행하도록 설정 됨 
			conn = JDBCUtil.getConnection();
			dao = MovieDAO.getInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//connection close
	@After
	public void after() {
		JDBCUtil.close(conn);
	}
	
	@Test
	public void testInsert01() throws SQLException {
		Movie movie = new Movie(0, "굿바이 싱글",
				"대한민국 대표 독거스타의 임신 스캔들! 이번엔 제대로 사고쳤다! 온갖 찌라시와 스캔들의 주인공인 톱스타 ‘주연’(김혜수) 그러나 점차 내려가는 인기와 남자친구의 공개적 배신에 충격을 받고, 영원한 내 편을 만들기 위해 대책 없는 계획에 돌입하게 되는데! 대표 독거스타의 임신 발표는 전국민 스캔들로 일이 커지고, ‘주연’(김혜수)의 불알친구이자 스타일리스트인 ‘평구’(마동석)와 소속사 식구들이 안절부절하며 뒷수습에 동분서주 하는데… 통제불능 여배우! 그녀의 무모한 계획은 계속 될까?!",
				"goodbye.jpg",
				"09:15, 14:00, 16:30");
		dao.insert(conn, movie);
	}
	
	@Test
	public void testList() throws SQLException {
		List<Movie> list = dao.selectMovies(conn);
		for(Movie movie : list) {
			System.out.println(movie);
		}
	}

	
	
}
