<%@ page import="com.example.gestiondesnotes.model.Note" %>
<%@ page import="com.example.gestiondesnotes.model.Etudiant" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.gestiondesnotes.Utils.NoteUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails de l'Élément</title>
</head>

<body>

<h2>Détails de l'Élément</h2>

<%-- Récupérer l'élément de la requête --%>
<%
    com.example.gestiondesnotes.model.Element element = (com.example.gestiondesnotes.model.Element) request.getAttribute("element");
%>

<p>Nom de l'Élément: <%= element.getNom_element() %></p>

<h3>Liste des Étudiants</h3>

<%-- Récupérer la liste des étudiants de la requête --%>
<%
    List<com.example.gestiondesnotes.model.Etudiant> etudiants = (List<com.example.gestiondesnotes.model.Etudiant>) request.getAttribute("etudiants");
    List<Note> notes = (List<Note>) request.getAttribute("notes");
%>

<table border="1">
    <thead>
    <tr>
        <th>Nom de l'Étudiant</th>
        <th>Prénom de l'Étudiant</th>
        <th>CC</th>
        <th>TP</th>
        <th>Projet</th>
        <th>Présentations</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <%-- Boucle sur la liste des étudiants --%>
    <% for (Etudiant etudiant : etudiants) { %>
    <tr>
        <td><%= etudiant.getNom_etudiant() %></td>
        <td><%= etudiant.getPrenom_etudiant() %></td>
        <%-- Appel de la méthode statique de NoteUtils pour obtenir la note de l'étudiant --%>
        <% Note note = NoteUtils.getNoteForEtudiant(etudiant, notes); %>
        <td><%= note.isAbsence_cc() ? "Absent" : String.valueOf(note.getCc()) %></td>
        <td><%= note.isAbsence_tp() ? "Absent" : String.valueOf(note.getTp()) %></td>
        <td><%= note.isAbsence_projet() ? "Absent" : String.valueOf(note.getProjet()) %></td>
        <td><%= note.isAbsence_presentations() ? "Absent" : String.valueOf(note.getPresentations()) %></td>
        <td>
            <a href="modifierNote?code_etudiant=<%= note.getCode_etudiant() %>">Modifier</a> |
            <a href="supprimerNote?code_etudiant=<%= note.getCode_etudiant() %>">Supprimer</a> |
            <a href="validerElement?code_etudiant=<%= note.getCode_etudiant() %>">Valider</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

<br>

</body>
</html>
