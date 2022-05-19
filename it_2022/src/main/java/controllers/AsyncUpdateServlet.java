package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import repositories.Repository;

@WebServlet("/updateInfo")
public class AsyncUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Repository collection;
	Gson gson;
 
	public void init(ServletConfig config) throws ServletException {
		collection = Repository.getInstance();
		gson = new Gson();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = request.getReader().readLine()) != null) {
		    sb.append(s);
		}

		User newUser = (User) gson.fromJson(sb.toString(), User.class);
		
		User user = collection.getUserById(newUser.getId());
		
		user.setPersonalName(newUser.getPersonalName());
		user.setJobTitle(newUser.getJobTitle());
		user.setDescription(newUser.getDescription());
		JsonResponse jr = new JsonResponse();
		
		if(user!=null) {
			jr.setMessage("Успешна актуализация");
		}
		else {
			jr.setMessage("Неуспешна актуализация");
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(gson.toJson(jr)); 
		out.flush();
		
	}

}
