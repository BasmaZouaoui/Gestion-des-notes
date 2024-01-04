package com.example.gestiondesnotes.dao.dao_impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.gestiondesnotes.dao.connexioJDBC.Connexion;
import com.example.gestiondesnotes.dao.NoteDAO;
import com.example.gestiondesnotes.model.Note;

public class NoteDAOImpl implements NoteDAO {

    private static final String GET_BY_ELEMENTS_QUERY = "SELECT * FROM note WHERE code_element=?";
    private static final String GET_BY_ETUDIANTS_QUERY = "SELECT * FROM note WHERE code_etudiant=?";
    private static final String SAVE_QUERY = "INSERT INTO note (cc, tp, projet, presentations, absence_cc, absence_tp, absence_projet, absence_presentations, code_etudiant, code_element,valide, note_total_ligne) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE note SET cc=?, tp=?, projet=?, presentations=?, absence_presentations=?, absence_cc=?, absence_tp=?, absence_projet=? WHERE code_etudiant=? AND code_element=?";
    private static final String DELETE_QUERY = "DELETE FROM note WHERE note_id=?";

    @Override
    public Note getNoteById(int note_id) {
        Note note = null;

        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement("SELECT * FROM Note WHERE note_id = ?")) {
            statement.setInt(1, note_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    note = extractNoteFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return note;
    }

    @Override
    public List<Note> getNotesByEtudiant(int etudiantID) {
        List<Note> notes = new ArrayList<>();

        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_BY_ETUDIANTS_QUERY)) {
            statement.setInt(1, etudiantID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Note note = extractNoteFromResultSet(resultSet);
                    notes.add(note);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    @Override
    public void add(Note entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, entity.getCc());
            statement.setDouble(2, entity.getTp());
            statement.setDouble(3, entity.getProjet());
            statement.setDouble(4, entity.getPresentations());
            statement.setBoolean(5, entity.isAbsence_cc());
            statement.setBoolean(6, entity.isAbsence_tp());
            statement.setBoolean(7, entity.isAbsence_projet());
            statement.setBoolean(8, entity.isAbsence_presentations());
            statement.setInt(9, entity.getCode_etudiant());
            statement.setInt(10, entity.getCode_element());
            statement.setBoolean(11, entity.isValide());
            statement.setDouble(12, entity.getNote_total_ligne());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setNote_id(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Note entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setDouble(1, entity.getCc());
            statement.setDouble(2, entity.getTp());
            statement.setDouble(3, entity.getProjet());
            statement.setDouble(4, entity.getPresentations());
            statement.setInt(5, entity.getCode_etudiant()); // For code_etudiant in WHERE clause
            statement.setInt(6, entity.getCode_element()); // For code_element in WHERE clause, also set above
            statement.setBoolean(7, entity.isAbsence_cc());
            statement.setBoolean(8, entity.isAbsence_tp());
            statement.setBoolean(9, entity.isAbsence_projet());
            statement.setBoolean(10, entity.isAbsence_presentations());
            // The order here is important. Since we're using code_etudiant and code_element to identify the record, they come last.

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void delete(int noteID) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, noteID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Note> getNotesByElementModule(int code_element) {
        List<Note> notes = new ArrayList<>();

        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_BY_ELEMENTS_QUERY)) {
            statement.setInt(1, code_element);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    Note note = extractNoteFromResultSet(resultSet);
                    notes.add(note);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    @Override
    public void validerElement(int code_etudiant) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(
                "UPDATE Note SET valide=true WHERE code_etudiant=?")) {

            statement.setInt(1, code_etudiant);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Note extractNoteFromResultSet(ResultSet resultSet) throws SQLException {
        Note note = new Note();
        note.setNote_id(resultSet.getInt("note_id"));
        note.setCc(resultSet.getDouble("cc"));
        note.setTp(resultSet.getDouble("tp"));
        note.setProjet(resultSet.getDouble("projet"));
        note.setPresentations(resultSet.getDouble("presentations"));
        note.setAbsence_cc(resultSet.getBoolean("absence_cc"));
        note.setAbsence_tp(resultSet.getBoolean("absence_tp"));
        note.setAbsence_projet(resultSet.getBoolean("absence_projet"));
        note.setAbsence_presentations(resultSet.getBoolean("absence_presentations"));
        note.setCode_etudiant(resultSet.getInt("code_etudiant"));
        note.setCode_element(resultSet.getInt("code_element"));
        return note;
    }
}
