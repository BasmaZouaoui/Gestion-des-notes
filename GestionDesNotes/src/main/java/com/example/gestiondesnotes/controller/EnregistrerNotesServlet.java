package com.example.gestiondesnotes.controller;

import com.example.gestiondesnotes.dao.dao_impl.NoteDAOImpl;
import com.example.gestiondesnotes.dao.dao_impl.ModaliteEDAOImpl;
import com.example.gestiondesnotes.model.Note;
import com.example.gestiondesnotes.model.modalite_e;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@WebServlet("/enregistrerNotes")
public class EnregistrerNotesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NoteDAOImpl noteDAO = new NoteDAOImpl();
        ModaliteEDAOImpl modaliteEDAO = new ModaliteEDAOImpl();
//        int codeElement = Integer.parseInt(request.getParameter("codeElement"));
//        String[] codesEtudiants = request.getParameterValues("codesEtudiants");

        StringBuilder buffer = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }

        String jsonData = buffer.toString();

        // Parse the JSON data
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonData);

            List <Note> ListeNote = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int codeElement = jsonObject.getInt("code_element");
                int codeEtudiant = jsonObject.getInt("id");
                String cc = jsonObject.optString("cc");
                String presentation = jsonObject.optString("presentation");
                String projet = jsonObject.optString("projet");
                String tp = jsonObject.optString("tp");


                // Attempt to retrieve an existing note
                List<Note> existingNotes = noteDAO.getNotesByEtudiant(codeEtudiant);
                Note note = existingNotes.stream()
                        .filter(n -> n.getCode_element() == codeElement)
                        .findFirst()
                        .orElse(null);

                // Retrieve modalite_e using codeElement
                List<modalite_e> modaliteEs = modaliteEDAO.findModaliteEByCodeElement(codeElement);
                // Assuming there is only one ModaliteE per codeElement
                modalite_e modaliteE = modaliteEs.stream().findFirst().orElse(null);

                // Set properties from the form
                if ("A".equals(cc)) {
                    note.setCc(0);
                    note.setAbsence_cc(true);
                }else{
                    note.setCc(jsonObject.getDouble("cc"));
                    note.setAbsence_cc(false);
                }
                if ("A".equals(tp)) {
                    note.setTp(0);
                    note.setAbsence_tp(true);
                }else{
                    note.setTp(jsonObject.getDouble("tp"));
                    note.setAbsence_tp(false);
                }
                if ("A".equals(projet)) {
                    note.setProjet(0);
                    note.setAbsence_projet(true);
                }else{
                    note.setProjet(jsonObject.getDouble("projet"));
                    note.setAbsence_projet(false);
                }
                if ("A".equals(presentation)) {
                    note.setPresentations(0);
                    note.setAbsence_presentations(true);
                }else{
                    note.setPresentations(jsonObject.getDouble("presentation"));
                    note.setAbsence_presentations(false);
                }
                // Set additional properties
                note.setCode_etudiant(codeEtudiant);
                note.setCode_element(codeElement);

                double note_total_ligne = (note.getCc()*modaliteE.getP_cc() + note.getTp()*modaliteE.getP_projet() + note.getPresentations()*modaliteE.getP_presentation() + note.getProjet()*modaliteE.getP_tp());
                double truncatedNumber = (long)(note_total_ligne * 100) / 100.0;
                note.setNote_total_ligne(truncatedNumber);

                Boolean valide ;
                if (note_total_ligne >= 12){
                    valide = true;
                }else {
                    valide = false;
                }
                note.setValide(valide);

                System.out.println("-----------------------------------");
                System.out.println("code etudiant : " + codeEtudiant);
                System.out.println("note CC : "+ note.getCc());
                System.out.println("note TP : "+ note.getTp());
                System.out.println("note projet : "+ note.getProjet());
                System.out.println("note presentation : "+ note.getPresentations());
                System.out.println("pourcentage de calcule de cc"+modaliteE.getP_cc());
                System.out.println("pourcentage de calcule de presentation"+modaliteE.getP_presentation());
                System.out.println("pourcentage de calcule de projet"+modaliteE.getP_projet());
                System.out.println("pourcentage de calcule de tp"+modaliteE.getP_tp());
                System.out.println("note total de l'etudiant : " + note.getNote_total_ligne());
                System.out.println("validation : "+ note.isValide());
                System.out.println("absence cc : " + note.isAbsence_cc());
                System.out.println("absence tp : " + note.isAbsence_tp());
                System.out.println("absence projet : " + note.isAbsence_projet());
                System.out.println("absence presentation : " + note.isAbsence_presentations());
                System.out.println("-----------------------------------");


                noteDAO.delete(note.getNote_id());
                noteDAO.add(note);

                ListeNote.add(note);
                // If the note already exists in the database (note_id > 0), update it; otherwise, add it
            }

            response.setContentType("text/plain");
            response.getWriter().print("Element enregister avec succes");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
