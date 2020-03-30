package com.yi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yi.jdbc.JDBCUtil;
import com.yi.model.Movie;

public class MovieDAO {
	private static final MovieDAO dao = new MovieDAO();
	
	public static MovieDAO getInstance() {
		return dao;
	}
	
	private MovieDAO() {};
	
	
	public List<Movie> selectMovies(Connection conn )throws SQLException{
		PreparedStatement pstmt = null;
		String sql = "select * from movie";
		ResultSet rs = null;
		List<Movie> list = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					Movie movie = new Movie(rs.getInt(1),
											rs.getString(2),
											rs.getString(3),
											rs.getString(4),
											rs.getString(5));
					list.add(movie);
					
				}while(rs.next());
			}
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return list;
		
	}
	
	public Movie selectMovieByNo(Connection conn, int no)throws SQLException{
		PreparedStatement pstmt = null;
		String sql = "select * from movie where no = ?";
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					Movie movie = new Movie(rs.getInt(1),
											rs.getString(2),
											rs.getString(3),
											rs.getString(4),
											rs.getString(5));
					
					return movie;
				}while(rs.next());
			}
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return null;
		
	}
	
	
	public void insert(Connection conn, Movie movie) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into movie values(0,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movie.getTitle());
			pstmt.setString(2, movie.getContent());
			pstmt.setString(3, movie.getFile());
			pstmt.setString(4, movie.getTime());
			
			pstmt.executeUpdate();
			
		}finally {
			JDBCUtil.close(pstmt);
		}
	}

	
	
}
