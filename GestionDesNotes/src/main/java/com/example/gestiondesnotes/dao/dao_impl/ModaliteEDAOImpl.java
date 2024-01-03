package com.example.gestiondesnotes.dao.dao_impl;

import com.example.gestiondesnotes.dao.ModaliteEDAO;
import com.example.gestiondesnotes.dao.connexioJDBC.Connexion;
import com.example.gestiondesnotes.model.modalite_e;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModaliteEDAOImpl implements ModaliteEDAO {

    @Override
    public void add(modalite_e entity) {

    }

    @Override
    public void update(modalite_e entity) {

    }

    @Override
    public void delete(int id) {

    }

    private static final String GET_BY_CODE_ELEMENT_QUERY = "SELECT * FROM modalite_e WHERE code_element=?";

    // Les autres requêtes SQL nécessaires pour les opérations CRUD peuvent être ajoutées ici

    @Override
    public modalite_e findModaliteEById(int id) {
        // Implémentez cette méthode selon vos besoins
        return null;
    }

    @Override
    public List<modalite_e> findAllModaliteEs() {
        // Implémentez cette méthode selon vos besoins
        return null;
    }

    @Override
    public List<modalite_e> findModaliteEByCodeElement(int codeElement) {
        List<modalite_e> modaliteEs = new ArrayList<>();

        try (PreparedStatement statement = Connexion.getInstance().getConnection().prepareStatement(GET_BY_CODE_ELEMENT_QUERY)) {
            statement.setInt(1, codeElement);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    modalite_e modaliteE = extractModaliteEFromResultSet(resultSet);
                    modaliteEs.add(modaliteE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modaliteEs;
    }

    private modalite_e extractModaliteEFromResultSet(ResultSet resultSet) throws SQLException {
        modalite_e modaliteE = new modalite_e();
        modaliteE.setId_modalite(resultSet.getInt("id_modalite"));
        modaliteE.setP_cc(resultSet.getFloat("p_cc"));
        modaliteE.setP_tp(resultSet.getFloat("p_tp"));
        modaliteE.setP_projet(resultSet.getFloat("p_projet"));
        modaliteE.setP_presentation(resultSet.getFloat("p_presentation"));
        modaliteE.setCode_element(resultSet.getInt("code_element"));
        // Définissez ici les autres champs de l'objet modaliteE selon votre modèle de données
        return modaliteE;
    }

    // Implémentez ici les autres méthodes CRUD pour la classe ModaliteE

}
