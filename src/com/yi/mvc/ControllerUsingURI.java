package com.yi.mvc;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@SuppressWarnings("serial")
public class ControllerUsingURI extends HttpServlet {
	private HashMap<String, CommandHandler> commandHandlerMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		//Map 안에 command와 class가 담기게 처리함
		//xml 파일에 있는 param-name 이랑 같아야함
		String configFile = getInitParameter("configFile"); //properties 파일 위치
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile); //절대주소를 가져옴
		try(FileReader fis = new FileReader(configFilePath)){ //properties 파일을 열어서 읽음
			prop.load(fis);
		}catch(Exception e) {
			throw new ServletException(e);
		}
		
		Iterator keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next(); //simple.do
			String handlerClassName = prop.getProperty(command); //com.yi.handler.SimpleHandler
			try {//단순한 클래스명 (handlerClassName)을 클래스화 하는 코드 
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				commandHandlerMap.put(command, handlerInstance);
			}catch(Exception e) {
				throw new ServletException(e);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//command를 뽑아내기
		String command = request.getRequestURI();
		if(command.indexOf(request.getContextPath())==0) {
			// /simple.do
			command = command.substring(request.getContextPath().length());
		}
		
		CommandHandler handler = commandHandlerMap.get(command);
		if(handler == null) {//command가 simple.do가 아닌 없는 command 일 경우
			handler = new NullHandler();
		}
		String viewPage = null;
		
		try {
			viewPage = handler.process(request, response); // simpleForm.jsp
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		if(viewPage != null) { //viewPage가 null 이 아닐 때 forward
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
	
}
