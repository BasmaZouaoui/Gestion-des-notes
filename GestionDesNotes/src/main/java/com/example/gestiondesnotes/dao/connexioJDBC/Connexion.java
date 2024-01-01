package com.example.gestiondesnotes.dao.connexioJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private Connection connexion;
    private static Connexion instance = null;

    private Connexion() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/gestion_notes";
            String username = "postgres";
            String password = "root";

            connexion = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Singleton
    public static synchronized Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    // Méthode pour récupérer la connexion
    public Connection getConnection() {
        return connexion;
    }

    // Méthode pour fermer la connexion
    public void closeConnection() {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
