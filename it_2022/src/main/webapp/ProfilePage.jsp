<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.Skill" %>

<!DOCTYPE html>
<html>
<jsp:include page="shared/head.jsp"/>

<body>
<!--  
<jsp:useBean id="loggedUser" class="model.User" scope="request"/>
<jsp:setProperty property="personalName" name="loggedUser" />
<jsp:getProperty property="personalName" name="loggedUser" />-->

<% User user = (User)request.getAttribute("loggedUser"); %>

	<jsp:include page="shared/header.jsp"/>

	<div class="content">
	<% if(request.getAttribute("message")==null) { %>
	
	<h2>Добре дошли!</h2>
	<form action="cookie" method="post">
	<input type="submit" value="Изключи" />
	</form>
	<% } %>
		<div>
			<div class="profile-image-container">
				<img src="images/male.svg" />
			</div>
			<div class="profile-info-container">
				<h2>Профилна информация</h2>
				
				<p><a href="user?id=<%= user.getId() %>&action=edit">Редактирай профила</a></p>
				<p>Име:  <%= user.getPersonalName() %></p>
				<p>Професия: <%= user.getJobTitle() %></p>
				<p>Описание: <%= user.getDescription() %></p>

			</div>
		</div>
		<h2>Умения</h2>

		<div class="skills-container">
			<div class="skills-container-element">
						<% for(Skill skill:user.getProfSkills()) { %>
				
				<div>
					<label><%= skill.getSkillName() %></label>
					<div class="outer-progress">
						<div class="inner-progress" style="width: <%= skill.getSkillValue() %>%"></div>
					</div>
				</div>
				<% } %>
 
			</div>

			<div class="skills-container-element">
			<% for(Skill skill:user.getPersonalSkills()) { %>
				
				<div>
					<label><%= skill.getSkillName() %></label>
					<div class="outer-progress">
						<div class="inner-progress" style="width: <%= skill.getSkillValue() %>%"></div>
					</div>
				</div>
				<% } %>
			 
			</div>
		</div>
		<h2>Контакти</h2>

		<div class="skills-container">
			<div class="skills-container-element">
				<div>
					<label>E-mail</label>
					 <p class="profile-info-in-orange"><%= user.getEmail() %></p>
				</div>

				<div>
					<label>Град</label>
					 <p class="profile-info-in-orange"><%= user.getAddress().getCity() %></p>
				</div>

			</div>

			<div class="skills-container-element">
				<div>
					<label>Телефон</label>
					 <p class="profile-info-in-orange"><%= user.getPhone() %></p>
					 
				</div>

				<div>
					<label>Улица</label>
					 <p class="profile-info-in-orange"><%= user.getAddress().getStreet() %></p>				 
				</div>			
			</div>
		</div>
	</div>
	<jsp:include page="shared/footer.jsp"/>

</body>
</html>
