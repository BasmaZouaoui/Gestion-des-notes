package com.example.gestiondesnotes.dao.connexioJDBC;

import com.example.gestiondesnotes.dao.ElementDAO;
import com.example.gestiondesnotes.dao.dao_impl.ElementDAOImpl;
import com.example.gestiondesnotes.model.Element;

import java.util.List;

public class main {

    public static void main(String[] args) {
        // Remplacez le code_prof avec le code du professeur que vous souhaitez tester
        int codeProf = 1; // Remplacez ceci par le code_prof que vous souhaitez tester

        ElementDAO elementDAO = new ElementDAOImpl(); // Assurez-vous d'avoir une classe ElementDAO

        List<Element> elements = elementDAO.getByProf(codeProf);

        // Afficher les éléments obtenus
        for (Element element : elements) {
            System.out.println("Code_element: " + element.getCode_element());
            System.out.println("Nom_element: " + element.getNom_element());
            System.out.println("Coefficient: " + element.getCoefficient());
            System.out.println("Code_module: " + element.getCode_module());
            System.out.println("--------------");
        }
    }
}
