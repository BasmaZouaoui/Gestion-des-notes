package com.example.gestiondesnotes.controller;

import com.example.gestiondesnotes.dao.ElementDAO;
import com.example.gestiondesnotes.dao.EtudiantDAO;
import com.example.gestiondesnotes.dao.NoteDAO;
import com.example.gestiondesnotes.dao.dao_impl.ElementDAOImpl;
import com.example.gestiondesnotes.dao.dao_impl.EtudiantDAOImpl;
import com.example.gestiondesnotes.dao.dao_impl.NoteDAOImpl;
import com.example.gestiondesnotes.model.Element;
import com.example.gestiondesnotes.model.Etudiant;
import com.example.gestiondesnotes.model.Note;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/listeNotes")
public class ListeNotesServlet extends HttpServlet {
    private ElementDAO elementDAO = new ElementDAOImpl();
    private NoteDAO noteDAO = new NoteDAOImpl();
    private EtudiantDAO etudiantDAO = new EtudiantDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codeElement = Integer.parseInt(request.getParameter("code_element"));

        System.out.println(codeElement);

        // Récupérer l'élément par code
        Element element = elementDAO.getById(codeElement);

        // Récupérer la liste des étudiants associés à l'élément avec les détails des notes
        List<Etudiant> etudiants = etudiantDAO.getEtudiantsByElement(codeElement);

        // Récupérer la liste des notes associées à l'élément
        List<Note> notes = noteDAO.getNotesByElementModule(codeElement);

        request.setAttribute("element", element);
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("notes", notes);  // Transmettre la liste de notes à la page JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListeNotes.jsp");
        dispatcher.forward(request, response);
    }
}
