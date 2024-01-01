<%@ page import="com.example.gestiondesnotes.model.Note" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier la Note</title>
</head>

<body>

<h2>Modifier la Note</h2>

<%-- Récupérer la note de la requête --%>
<%
    Note note = (Note) request.getAttribute("note");
%>

<form action="modifierNote" method="post">
    <input type="hidden" name="code_etudiant" value="<%= note.getCode_etudiant() %>">

    <label for="cc">CC:</label>
    <input type="text" name="cc" value="<%= note.getCc() %>"><br>

    <label for="tp">TP:</label>
    <input type="text" name="tp" value="<%= note.getTp() %>"><br>

    <label for="projet">Projet:</label>
    <input type="text" name="projet" value="<%= note.getProjet() %>"><br>

    <label for="presentations">Présentations:</label>
    <input type="text" name="presentations" value="<%= note.getPresentations() %>"><br>

    <input type="submit" value="Modifier">
</form>

</body>
</html>
