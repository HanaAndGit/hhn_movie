package com.yi.handler.movie;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yi.dao.MovieDAO;
import com.yi.jdbc.JDBCUtil;
import com.yi.model.Movie;
import com.yi.mvc.CommandHandler;

public class MovieDetailHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int no = Integer.parseInt(req.getParameter("no"));
		
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			MovieDAO dao = MovieDAO.getInstance();
			Movie movie = dao.selectMovieByNo(conn, no);
			req.setAttribute("movie", movie);
			
			return "/WEB-INF/view/movie/movieDetail.jsp";
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	}

}
