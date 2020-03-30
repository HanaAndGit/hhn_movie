package com.yi.handler.movie;

import java.io.File;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yi.dao.MovieDAO;
import com.yi.jdbc.JDBCUtil;
import com.yi.model.Movie;
import com.yi.mvc.CommandHandler;

public class MovieAddHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/movie/movieAddForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")) {
			String uploadPath = req.getRealPath("upload"); //폴더 명
			File dir = new File(uploadPath);
			if(dir.exists()==false) { //폴더가 없으면 폴더 만듦
				dir.mkdir();
			}
			int size = 1024*1024*10; // == 10MB
			MultipartRequest multi = new MultipartRequest(req, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
			
			Connection conn = null;
			try {
				conn = JDBCUtil.getConnection();
				MovieDAO dao = MovieDAO.getInstance();
				Movie movie = new Movie(0, multi.getParameter("title"),    
										multi.getParameter("content"),
										multi.getFilesystemName("file"),
										multi.getParameter("time"));
				dao.insert(conn, movie);
				res.sendRedirect("list.do");
				return null;
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
		}
		return null;
	}

}
