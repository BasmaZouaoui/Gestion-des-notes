package com.example.gestiondesnotes.Utils;

import com.example.gestiondesnotes.model.Etudiant;
import com.example.gestiondesnotes.model.Note;

import java.util.List;

// NoteUtils.java
public class NoteUtils {
    public static Note getNoteForEtudiant(Etudiant etudiant, List<Note> notes) {
        for (Note note : notes) {
            if (note.getCode_etudiant() == etudiant.getCode_etudiant()) {
                return note;
            }
        }
        return new Note(); // Retourne une note si aucune note n'est trouvée (à adapter selon votre logique)
    }
}
