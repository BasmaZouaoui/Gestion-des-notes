package com.example.gestiondesnotes.dao;

import java.util.List;

import com.example.gestiondesnotes.model.Note;

public interface NoteDAO extends GenericDAO<Note> {

    List<Note> getNotesByElementModule(int code_element);
    Note getNoteById(int note_id);
    List<Note> getNotesByEtudiant(int code_etudiant);
    void validerElement(int codeElement);


}
