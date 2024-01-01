package com.example.gestiondesnotes.controller;

import com.example.gestiondesnotes.dao.NoteDAO;
import com.example.gestiondesnotes.dao.dao_impl.NoteDAOImpl;
import com.example.gestiondesnotes.model.Note;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/validerElement")
public class ValiderElementServlet extends HttpServlet {
    private NoteDAO noteDAO = new NoteDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codeEtudiant = Integer.parseInt(request.getParameter("code_etudiant"));

        // Récupérer les notes associées à l'étudiant
        List<Note> notes = noteDAO.getNotesByEtudiant(codeEtudiant);

        // Vérifier si toutes les notes ont été saisies
        if (!saisieNotesComplete(notes)) {
            // Rediriger avec un message d'erreur
            response.sendRedirect("ListeNotes?error=saisie_incomplete");
            return;
        }

        // Vérifier si toutes les notes sont comprises entre 0 et 20
        if (!notesValides(notes)) {
            // Rediriger avec un message d'erreur
            response.sendRedirect("ListeNotes?error=notes_invalides");
            return;
        }

        if (contientZeroOuVingt(notes)) {
            // Afficher une boîte de dialogue JavaScript pour demander la confirmation du professeur
            String confirmationScript = "if (confirm('Des notes sont égales à 0 ou 20. Voulez-vous vraiment valider l\'élément ?')) { "
                    + "window.location.href = 'validerElement?code_etudiant=" + codeEtudiant + "'; "
                    + "} else { "
                    + "window.location.href = 'ListeNotes'; "
                    + "}";

            response.getWriter().println("<script>" + confirmationScript + "</script>");
            return;
        }

        // Valider l'élément
        noteDAO.validerElement(codeEtudiant);

        // Rediriger vers la page listeEtudiants avec un message de succès
        response.sendRedirect("ListeNotes?success=element_valide");
    }

    // Méthode pour vérifier si toutes les notes ont été saisies
    private boolean saisieNotesComplete(List<Note> notes) {
        for (Note note : notes) {
            if (note.getCc() == 0 || note.getTp() == 0 || note.getProjet() == 0 || note.getPresentations() == 0) {
                return false; // Une note est manquante
            }
        }
        return true; // Toutes les notes ont été saisies
    }

    // Méthode pour vérifier si toutes les notes sont comprises entre 0 et 20
    private boolean notesValides(List<Note> notes) {
        for (Note note : notes) {
            if (note.getCc() < 0 || note.getCc() > 20 ||
                    note.getTp() < 0 || note.getTp() > 20 ||
                    note.getProjet() < 0 || note.getProjet() > 20 ||
                    note.getPresentations() < 0 || note.getPresentations() > 20) {
                return false; // Au moins une note n'est pas valide
            }
        }
        return true; // Toutes les notes sont valides
    }

    // Méthode pour vérifier s'il y a des 0 ou des 20 parmi les notes
    private boolean contientZeroOuVingt(List<Note> notes) {
        for (Note note : notes) {
            if (note.getCc() == 0 || note.getCc() == 20 ||
                    note.getTp() == 0 || note.getTp() == 20 ||
                    note.getProjet() == 0 || note.getProjet() == 20 ||
                    note.getPresentations() == 0 || note.getPresentations() == 20) {
                return true; // Au moins une note est 0 ou 20
            }
        }
        return false; // Aucune note n'est 0 ou 20
    }
}
