package com.example.gestiondesnotes.dao.dao_impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.gestiondesnotes.dao.ProfesseurDAO;
import com.example.gestiondesnotes.dao.connexioJDBC.Connexion;
import com.example.gestiondesnotes.model.Professeur;

public class ProfesseurDAOImpl implements ProfesseurDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM Professeur WHERE code_prof=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM Professeur";
    private static final String SAVE_QUERY = "INSERT INTO Professeur (code_prof, nom_prof, prenom_prof, specialite) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Professeur SET nom_prof=?, prenom_prof=?, specialite=? WHERE code_prof=?";
    private static final String DELETE_QUERY = "DELETE FROM Professeur WHERE code_prof=?";
    private static final String CNX_QUERY = "SELECT * FROM professeur WHERE username = ? AND password = ?";

    @Override
    public Professeur getProfesseurByUsernameAndPassword(String username, String password) {

        Professeur professeur = null;

        try (PreparedStatement preparedStatement = Connexion.getInstance().getConnection().prepareStatement(CNX_QUERY)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                professeur = new Professeur();
                professeur.setCode_prof(resultSet.getInt("code_prof"));
                professeur.setNom_prof(resultSet.getString("nom_prof"));
                professeur.setPrenom_prof(resultSet.getString("prenom_prof"));
                professeur.setSpecialite(resultSet.getString("specialite"));
                professeur.setUsername(resultSet.getString("username"));
                professeur.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professeur;
    }

    @Override
    public Professeur getById(int code_prof) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, code_prof);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractProfesseurFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Professeur> getAll() {
        List<Professeur> professeurs = new ArrayList<>();

        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Professeur professeur = extractProfesseurFromResultSet(resultSet);
                professeurs.add(professeur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeurs;
    }

    @Override
    public void add(Professeur entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(SAVE_QUERY)) {
            statement.setLong(1, entity.getCode_prof());
            statement.setString(2, entity.getNom_prof());
            statement.setString(3, entity.getPrenom_prof());
            statement.setString(4, entity.getSpecialite());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Professeur entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, entity.getNom_prof());
            statement.setString(2, entity.getPrenom_prof());
            statement.setString(3, entity.getSpecialite());
            statement.setInt(4, entity.getCode_prof());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int code_prof) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, code_prof);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Professeur extractProfesseurFromResultSet(ResultSet resultSet) throws SQLException {
        Professeur professeur = new Professeur();
        professeur.setCode_prof(resultSet.getInt("code_prof"));
        professeur.setPrenom_prof(resultSet.getString("nom_prof"));
        professeur.setPrenom_prof(resultSet.getString("prenom_prof"));
        professeur.setSpecialite(resultSet.getString("specialite"));
        professeur.setUsername(resultSet.getString("username"));
        professeur.setPassword(resultSet.getString("password"));
        return professeur;
    }
}
