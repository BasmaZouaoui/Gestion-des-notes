package com.example.gestiondesnotes.dao.dao_impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.gestiondesnotes.dao.connexioJDBC.Connexion;
import com.example.gestiondesnotes.dao.FiliereDAO;
import com.example.gestiondesnotes.model.Filiere;

public class FiliereDAOImpl implements FiliereDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM Filiere WHERE code_filiere=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM Filiere";
    private static final String SAVE_QUERY = "INSERT INTO Filiere (code_filiere, nom_filiere) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Filiere SET nom_filiere=? WHERE filiereId=?";
    private static final String DELETE_QUERY = "DELETE FROM Filiere WHERE code_filiere=?";

    @Override
    public Filiere getById(int code_filiere) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, code_filiere);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractFiliereFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Filiere> getAll() {
        List<Filiere> filieres = new ArrayList<>();

        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Filiere filiere = extractFiliereFromResultSet(resultSet);
                filieres.add(filiere);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filieres;
    }

    @Override
    public void add(Filiere entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(SAVE_QUERY)) {
            statement.setLong(1, entity.getCode_filiere());
            statement.setString(2, entity.getNom_filiere());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Filiere entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, entity.getNom_filiere());
            statement.setInt(2, entity.getCode_filiere());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int filiereId) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, filiereId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Filiere extractFiliereFromResultSet(ResultSet resultSet) throws SQLException {
        Filiere filiere = new Filiere();
        filiere.setCode_filiere(resultSet.getInt("code_filiere"));
        filiere.setNom_filiere(resultSet.getString("nom_filiere"));
        return filiere;
    }
}
