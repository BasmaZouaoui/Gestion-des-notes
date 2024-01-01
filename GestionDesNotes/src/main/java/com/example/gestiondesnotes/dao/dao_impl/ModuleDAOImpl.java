package com.example.gestiondesnotes.dao.dao_impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.gestiondesnotes.dao.ModuleDAO;
import com.example.gestiondesnotes.dao.connexioJDBC.Connexion;
import com.example.gestiondesnotes.model.module;

public class ModuleDAOImpl implements ModuleDAO {

    private static final String GET_BY_CODE_QUERY = "SELECT * FROM Module WHERE code_module=?";
    private static final String GET_ALL_QUERY = "SELECT * FROM Module";
    private static final String SAVE_QUERY = "INSERT INTO Module (code_module, nom_module, semestre, code_filiere) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Module SET nom_module=?, semestre=?, code_filiere=? WHERE code_module=?";
    private static final String DELETE_QUERY = "DELETE FROM Module WHERE code_module=?";

    @Override
    public module getById(int code_module) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_BY_CODE_QUERY)) {
            statement.setInt(1, code_module);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractModuleFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<module> getAllModules() {
        List<module> modules = new ArrayList<>();

        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                module mdl = extractModuleFromResultSet(resultSet);
                modules.add(mdl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

    @Override
    public void add(module entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(SAVE_QUERY)) {
            statement.setLong(1, entity.getCode_module());
            statement.setString(2, entity.getNom_module());
            statement.setString(3, entity.getSemestre());
            statement.setInt(4, entity.getCode_filiere());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(module entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, entity.getNom_module());
            statement.setString(2, entity.getSemestre());
            statement.setInt(3, entity.getCode_filiere());
            statement.setInt(4, entity.getCode_module());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int code_module) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, code_module);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private module extractModuleFromResultSet(ResultSet resultSet) throws SQLException {
        module module = new module();
        module.setCode_module(resultSet.getInt("codeModule"));
        module.setNom_module(resultSet.getString("nomModule"));
        module.setSemestre(resultSet.getString("semestre"));
        module.setCode_filiere(resultSet.getInt("code_filiere"));
        return module;
    }
}
