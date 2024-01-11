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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet("/listeNotes")
public class ListeNotesServlet extends HttpServlet {
    public ElementDAO elementDAO = new ElementDAOImpl();
    public NoteDAO noteDAO = new NoteDAOImpl();
    public EtudiantDAO etudiantDAO = new EtudiantDAOImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codeElement = Integer.parseInt(request.getParameter("code_element"));

        System.out.println(codeElement);

        // Récupérer l'élément par code
        Element element = elementDAO.getById(codeElement);

        // Récupérer la liste des étudiants associés à l'élément avec les détails des notes
        List<Etudiant> etudiants = etudiantDAO.getEtudiantsByElement(codeElement);

        // Récupérer la liste des notes associées à l'élément
        List<Note> notes = noteDAO.getNotesByElementModule(codeElement);

        System.out.println("---------------------------------------");
        System.out.println("code de l'element : "+element.getCode_element());
        System.out.println("nom de l'element : "+element.getNom_element());
        System.out.println("module de l'element : "+element.getCode_module());
        System.out.println("code du prof de l'element : "+element.getCode_prof());
        System.out.println("code du prof de l'element : "+element.getCoefficient());
        System.out.println("---------------------------------------");
        for (Etudiant etudiant : etudiants) {
            System.out.println("nom de l'etudiant : "+etudiant.getNom_etudiant());
            System.out.println("prenom de l'etudiant : "+etudiant.getPrenom_etudiant());
            System.out.println("code de l'etudiant : "+etudiant.getCode_etudiant());
            System.out.println("notes de l'etudiant : "+etudiant.getNotes());
            System.out.println("code de la filiere de l'etudiant : "+etudiant.getCode_filiere());
            System.out.println("---------------------------------------");
        }
        for (Note note : notes) {
            System.out.println("id de la note : "+note.getNote_id());
            System.out.println("code de l'etudiant de la note : "+note.getCode_etudiant());
            System.out.println("code de l'element de la note : "+note.getCode_element());
            System.out.println("cc de la note : "+note.getCc());
            System.out.println("tp de la note : "+note.getTp());
            System.out.println("projet de la note : "+note.getProjet());
            System.out.println("presentations de la note : "+note.getPresentations());
            System.out.println("classe de la note : "+note.getClass());
            System.out.println("absence dans cc de la note : "+note.isAbsence_cc());
            System.out.println("absence dans tp de la note : "+note.isAbsence_tp());
            System.out.println("absence dans projet de la note : "+note.isAbsence_projet());
            System.out.println("absence dans presentation de la note : "+note.isAbsence_presentations());
            System.out.println("validation de la note : "+note.isValide());
            System.out.println("---------------------------------------");
        }

        // Create a JSON array to hold your students and their notes
        JsonArray data = new JsonArray();
        for (Etudiant etudiant : etudiants) {
            for (Note note : notes) {
                if (note.getCode_etudiant() == etudiant.getCode_etudiant()) {
                    JsonObject jsonNote = new JsonObject();
                    jsonNote.addProperty("id", etudiant.getCode_etudiant());
                    jsonNote.addProperty("nom", etudiant.getNom_etudiant());
                    jsonNote.addProperty("prenom", etudiant.getPrenom_etudiant());
                    jsonNote.addProperty("tp", note.getTp());
                    jsonNote.addProperty("cc", note.getCc());
                    jsonNote.addProperty("projet", note.getProjet());
                    jsonNote.addProperty("presentation", note.getPresentations());

                    //double noteTotal = calculateNoteTotal(note); // Implement this method based on your logic
                    jsonNote.addProperty("note_total", note.getNote_total_ligne());
                    // Convert boolean to integer representation for 'valide' as shown in the desired format
                    jsonNote.addProperty("valide", note.isValide() ? 1 : 0);
                    data.add(jsonNote);


                }
            }
        }
        // Now wrap this array into a 'data' object
        JsonObject wrapper = new JsonObject();
        wrapper.add("data", data);

        // Convert the JSON object to a string
        String jsonData = new Gson().toJson(wrapper);

        System.out.println("---------------------------------------");
        System.out.println(jsonData);
        // Now jsonData contains the JSON string you can pass to your JSP
        request.setAttribute("jsonData", jsonData);

        request.setAttribute("element", element);
        request.setAttribute("etudiants", etudiants);
        request.setAttribute("notes", notes);  // Transmettre la liste de notes à la page JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListeNotes.jsp");
        dispatcher.forward(request, response);
    }
}
