<%@ page import="com.example.gestiondesnotes.model.Element" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Éléments</title>
</head>

<body>

<h2>Liste des Éléments</h2>

<%-- Récupérer la liste des éléments de la requête --%>
<%
    List<Element> elements = (List<Element>) request.getAttribute("elements");
%>

<ul>
    <%-- Boucle sur la liste des éléments --%>
    <% for (Element element : elements) { %>
    <li>
        <a href="listeNotes?code_element=<%= element.getCode_element() %>">
            <%= element.getNom_element() %>
        </a>
    </li>
    <% } %>
</ul>

<br>

</body>
</html>
