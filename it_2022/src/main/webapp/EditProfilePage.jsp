<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.Skill" %>

<!DOCTYPE html>
<html>
<jsp:include page="shared/head.jsp"/>

<body>

<% User user = (User)request.getAttribute("loggedUser"); %>

	<jsp:include page="shared/header.jsp"/>

	<div class="content">
	<form action="user" method="post">
		<div>
			<div class="profile-image-container">
				<img src="images/male.svg" />
			</div>
			<div class="profile-info-container">
				<h2>Профилна информация</h2>
				<p>Име: </p>
				<input type="text" name="personal-name" value="<%= user.getPersonalName() %>"/>
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
			</form>
		</div>
	</div>
	<jsp:include page="shared/footer.jsp"/>

</body>
</html>
