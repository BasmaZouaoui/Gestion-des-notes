package com.example.gestiondesnotes.dao.dao_impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.gestiondesnotes.dao.ElementDAO;
import com.example.gestiondesnotes.dao.connexioJDBC.Connexion;
import com.example.gestiondesnotes.model.Element;

public class ElementDAOImpl implements ElementDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM Element WHERE code_element=?";
    private static final String SAVE_QUERY = "INSERT INTO Element (code_element, nom_element, coefficient, code_module, code_prof) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Element SET nom_element=?, coefficient=?, code_module=?, code_prof=? WHERE code_element=?";
    private static final String DELETE_QUERY = "DELETE FROM Element WHERE code_element=?";

    @Override
    public Element getById(int code_element) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, code_element);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractElementModuleFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Element> getByProf(int code_prof) {
        List<Element> elements = new ArrayList<>();

        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement("SELECT * FROM Element WHERE code_prof = ?")) {
            statement.setInt(1, code_prof);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Element element = new Element();
                    element.setCode_element(resultSet.getInt("code_element"));
                    element.setNom_element(resultSet.getString("nom_element"));
                    element.setCoefficient(resultSet.getDouble("coefficient"));
                    element.setCode_module(resultSet.getInt("code_module"));
                    elements.add(element);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elements;
    }

    @Override
    public void add(Element entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(SAVE_QUERY)) {
            statement.setInt(1, entity.getCode_element());
            statement.setString(2, entity.getNom_element());
            statement.setDouble(3, entity.getCoefficient());
            statement.setInt(4, entity.getCode_module());
            statement.setInt(5, entity.getCode_prof());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Element entity) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, entity.getNom_element());
            statement.setDouble(2, entity.getCoefficient());
            statement.setInt(3, entity.getCode_module());
            statement.setInt(4, entity.getCode_prof());
            statement.setInt(5, entity.getCode_element());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int code_element) {
        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, code_element);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Element extractElementModuleFromResultSet(ResultSet resultSet) throws SQLException {
        Element elementModule = new Element();
        elementModule.setCode_element(resultSet.getInt("code_element"));
        elementModule.setNom_element(resultSet.getString("nom_element"));
        elementModule.setCoefficient(resultSet.getDouble("coefficient"));
        elementModule.setCode_module(resultSet.getInt("code_module"));
        elementModule.setCode_prof(resultSet.getInt("code_prof"));
        return elementModule;
    }

}
