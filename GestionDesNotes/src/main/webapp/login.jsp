<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 30/12/2023
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login Page</title>
</head>
<body>
<h2>Login Page</h2>

<%-- Affiche le message d'erreur en cas d'échec de connexion --%>
<% String error = request.getParameter("error");
  if (error != null && error.equals("1")) { %>
<p style="color: #bf0101;">Erreur d'authentification. Veuillez réessayer.</p>
<% } %>

<%-- Formulaire de connexion --%>
<form action="login" method="post">
  <label for="username">Nom d'utilisateur:</label>
  <input type="text" id="username" name="username" required><br>

  <label for="password">Mot de passe:</label>
  <input type="password" id="password" name="password" required><br>

  <input type="submit" value="Se connecter">
</form>
</body>
</html>
