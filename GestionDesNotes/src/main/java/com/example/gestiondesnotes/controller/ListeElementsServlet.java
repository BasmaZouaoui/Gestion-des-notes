package com.example.gestiondesnotes.controller;

import java.io.IOException;
import java.util.List;

import com.example.gestiondesnotes.dao.ElementDAO;
import com.example.gestiondesnotes.dao.dao_impl.ElementDAOImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.example.gestiondesnotes.model.Element;

@WebServlet("/listeElements")
public class ListeElementsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération de la session
        HttpSession session = request.getSession(false);

        // Vérification de la session de l'utilisateur
        if (session == null || session.getAttribute("codeProf") == null) {
            // L'utilisateur n'est pas connecté, rediriger vers la page de connexion
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            // L'utilisateur est connecté

            // Récupération du code du professeur connecté
            int codeProf = (int) session.getAttribute("codeProf");
            System.out.println("Code Professeur : " + codeProf);

            // Création d'un objet ElementDAO pour accéder à la base de données
            ElementDAO elementDAO = new ElementDAOImpl();

            // Récupération de la liste des éléments associés au professeur
            List<Element> elements = elementDAO.getByProf(codeProf);

            // Affichage des informations de débogage pour chaque élément
            System.out.println("Nombre d'éléments récupérés : " + elements.size());
            for (Element element : elements) {
                System.out.println("Code_element: " + element.getCode_element());
                System.out.println("Nom_element: " + element.getNom_element());
                System.out.println("Coefficient: " + element.getCoefficient());
                System.out.println("Code_module: " + element.getCode_module());
                System.out.println("--------------");
            }

            // Passage des résultats à la page JSP pour affichage
            request.setAttribute("elements", elements);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listeElements.jsp");
            dispatcher.forward(request, response);
        }
    }
}
