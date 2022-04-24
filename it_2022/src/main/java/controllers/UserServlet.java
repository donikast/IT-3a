package controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import repositories.Repository;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 Repository collection;
	public void init(ServletConfig config) throws ServletException {
			collection=Repository.getInstance();
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		int id= Integer.parseInt(request.getParameter("id"));
		String action = request.getParameter("action");
		
		User loggedUser = collection.getUserById(id);
		request.setAttribute("loggedUser", loggedUser);
		
		if(action!=null && !action.isEmpty() && action.equals("edit")) {
		RequestDispatcher rd = request.getRequestDispatcher("/EditProfilePage.jsp");
		rd.forward(request, response);
		} else {
		RequestDispatcher rd = request.getRequestDispatcher("/ProfilePage.jsp");
		rd.forward(request, response);	
		}
	}

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int id = Integer.parseInt(request.getParameter("id"));
		 User updatedUser = collection.getUserById(id);
		 
		 updatedUser.setPersonalName(request.getParameter("personal-name"));
		 updatedUser.setJobTitle(request.getParameter("job-title"));
		 updatedUser.setDescription(request.getParameter("description"));
		 updatedUser.setEmail(request.getParameter("email"));
		 updatedUser.setPhone(request.getParameter("phone"));
		 updatedUser.getAddress().setCity(request.getParameter("city"));
		 updatedUser.getAddress().setStreet(request.getParameter("street"));
		 
		 for(int i=0; i<updatedUser.getProfSkills().size(); i++) {
			String skillName = request.getParameter("it-skill-name"+i);
			updatedUser.getProfSkills().get(i).setSkillName(skillName);
			
			int skillValue = Integer.parseInt(request.getParameter("it-skill-value"+i));
			updatedUser.getProfSkills().get(i).setSkillValue(skillValue);
		 }
		 
		 response.sendRedirect("user?id="+updatedUser.getId());
	}
}
