package com.example.gestiondesnotes.controller;

import java.io.IOException;

import com.example.gestiondesnotes.dao.dao_impl.ProfesseurDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.gestiondesnotes.model.Professeur;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ProfesseurDAOImpl professeurDAO = new ProfesseurDAOImpl();

        Professeur professeur = professeurDAO.getProfesseurByUsernameAndPassword(username, password);

        if (professeur != null) {
            // L'authentification a réussi, enregistrez le professeur dans la session
            HttpSession session = request.getSession(true);
            session.setAttribute("codeProf", professeur.getCode_prof());

            // Rediriger vers la page des elements
            response.sendRedirect(request.getContextPath() + "/listeElements");
        } else {
            // L'authentification a échoué, redirigez vers une page de connexion avec un message d'erreur
            response.sendRedirect("login.jsp?error=1");
        }
    }
}