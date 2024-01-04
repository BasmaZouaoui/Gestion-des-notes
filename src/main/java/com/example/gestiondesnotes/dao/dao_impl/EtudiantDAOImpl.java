package com.example.gestiondesnotes.dao.dao_impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.gestiondesnotes.dao.connexioJDBC.Connexion;
import com.example.gestiondesnotes.dao.EtudiantDAO;
import com.example.gestiondesnotes.model.Etudiant;

public class EtudiantDAOImpl implements EtudiantDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM Etudiant WHERE code_etudiant=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM Etudiant";
    private static final String SAVE_QUERY = "INSERT INTO Etudiant (code_etudiant, nom_etudiant, prenom_etudiant, code_filiere) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Etudiant SET nom_etudiant=?, prenom_etudiant=?, code_filiere=? WHERE code_etudiant=?";
    private static final String DELETE_QUERY = "DELETE FROM Etudiant WHERE code_etudiant=?";
    private static final String GET_BY_ELEMENT_QUERY = "SELECT * FROM Etudiant WHERE code_etudiant IN (SELECT code_etudiant FROM Note WHERE code_element = ?)";

    @Override
    public Etudiant getById(int etudiantID) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, etudiantID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractEtudiantFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Etudiant> getAll() {
        List<Etudiant> etudiants = new ArrayList<>();

        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Etudiant etudiant = extractEtudiantFromResultSet(resultSet);
                etudiants.add(etudiant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }
    @Override
    public List<Etudiant> getEtudiantsByElement(int codeElement) {
        List<Etudiant> etudiants = new ArrayList<>();

        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_BY_ELEMENT_QUERY)) {
            statement.setInt(1, codeElement);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Etudiant etudiant = extractEtudiantFromResultSet(resultSet);
                    etudiants.add(etudiant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiants;
    }

    @Override
    public void add(Etudiant entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(SAVE_QUERY)) {
            statement.setInt(1, entity.getCode_etudiant());
            statement.setString(2, entity.getNom_etudiant());
            statement.setString(3, entity.getPrenom_etudiant());
            statement.setInt(4, entity.getCode_filiere());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Etudiant entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, entity.getNom_etudiant());
            statement.setString(2, entity.getPrenom_etudiant());
            statement.setInt(3, entity.getCode_filiere());
            statement.setInt(4, entity.getCode_etudiant());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int etudiantID) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, etudiantID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Etudiant extractEtudiantFromResultSet(ResultSet resultSet) throws SQLException {
        Etudiant etudiant = new Etudiant();
        etudiant.setCode_etudiant(resultSet.getInt("code_etudiant"));
        etudiant.setNom_etudiant(resultSet.getString("nom_etudiant"));
        etudiant.setPrenom_etudiant(resultSet.getString("prenom_etudiant"));
        etudiant.setCode_filiere(resultSet.getInt("code_filiere"));
        return etudiant;
    }
}
