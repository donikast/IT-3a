package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Skill;
import model.User;
import repositories.Repository;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Repository collection;
	
	public void init(ServletConfig config) throws ServletException {
		collection = Repository.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/RegistrationForm.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		request.setCharacterEncoding("UTF-8");
		String personalName = request.getParameter("personal-name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeat-password");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		if(personalName==null || personalName.isEmpty() ||
			username==null || username.isEmpty() ||	
			password==null || password.isEmpty() ||
			!password.equals(repeatPassword)) {
			
			out.print("<p>Не сте въвели всички полета!</p>");
			RequestDispatcher rd = request.getRequestDispatcher("/RegistrationForm.jsp");
			rd.include(request, response);
		} 
		else {
			User user = new User(personalName,username,password);
			if(collection.getUserByUserName(username)==null) {
				
				List<Skill> profSkills = new ArrayList<Skill>();
				profSkills.add(new Skill());
				profSkills.add(new Skill());
				profSkills.add(new Skill());
				profSkills.add(new Skill());
				
				user.setProfSkills(profSkills);
				
				List<Skill> personalSkills = new ArrayList<Skill>();
				personalSkills.add(new Skill());
				personalSkills.add(new Skill());
				personalSkills.add(new Skill());
				
				user.setPersonalSkills(personalSkills);
				
				collection.addUser(user);
				out.print("<p>Успешно регистриран потребител!</p>");
				
				response.sendRedirect("login");
				
			}
			else {
				out.print("<html><body><p>Потребителското име е заето!</p></body></html>");
				RequestDispatcher rd = request.getRequestDispatcher("/RegistrationForm.jsp");
				rd.include(request, response);
			}
		}
	}
}
