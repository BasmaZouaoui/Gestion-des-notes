package com.example.gestiondesnotes.controller;

import com.example.gestiondesnotes.dao.NoteDAO;
import com.example.gestiondesnotes.dao.dao_impl.NoteDAOImpl;
import com.example.gestiondesnotes.model.Note;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/modifierNote")
public class ModifierNoteServlet extends HttpServlet {
    private NoteDAO noteDAO = new NoteDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codeEtudiant = Integer.parseInt(request.getParameter("code_etudiant"));

        // Récupérer la note associée à l'étudiant
        Note note = (Note) noteDAO.getNotesByEtudiant(codeEtudiant);

        // Mettre la note dans la requête pour l'utiliser dans la JSP
        request.setAttribute("note", note);

        // Dispatcher vers la page de modification de note
        RequestDispatcher dispatcher = request.getRequestDispatcher("modifierNote.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codeEtudiant = Integer.parseInt(request.getParameter("code_etudiant"));
        double cc = Double.parseDouble(request.getParameter("cc"));
        double tp = Double.parseDouble(request.getParameter("tp"));
        double projet = Double.parseDouble(request.getParameter("projet"));
        double presentations = Double.parseDouble(request.getParameter("presentations"));

        // Récupérer la note associée à l'étudiant
        Note note = (Note) noteDAO.getNotesByEtudiant(codeEtudiant);

        // Mettre à jour les valeurs de la note
        note.setCc(cc);
        note.setTp(tp);
        note.setProjet(projet);
        note.setPresentations(presentations);

        // Mettre à jour la note dans la base de données
        noteDAO.update(note);

        // Rediriger vers la page des détails de l'élément avec un message de succès
        response.sendRedirect("detailsElement?code_element=" + note.getCode_element() + "&success=note_modifiee");
    }
}
