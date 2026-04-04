package fr.univamu.iut.platsutilisateurs;

import fr.univamu.iut.platsutilisateurs.plat.PlatRepositoryInterface;
import fr.univamu.iut.platsutilisateurs.plat.PlatRepositoryMariadb;
import fr.univamu.iut.platsutilisateurs.utilisateur.UtilisateurRepositoryInterface;
import fr.univamu.iut.platsutilisateurs.utilisateur.UtilisateurRepositoryMariadb;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe d'application JAX-RS pour l'API Plats et Utilisateurs
 */
@ApplicationPath("/api")
@ApplicationScoped
public class PlatsUtilisateursApplication extends Application {

    /**
     * URL de connexion a la base de donnees
     */
    private static final String DB_URL;

    /**
     * Identifiant de connexion a la base de donnees
     */
    private static final String DB_USER;

    /**
     * Mot de passe de connexion a la base de donnees
     */
    private static final String DB_PWD;

    static {
        Properties props = new Properties();
        try (InputStream input = PlatsUtilisateursApplication.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                System.err.println("Fichier db.properties introuvable dans le classpath");
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture db.properties: " + e.getMessage());
        }
        DB_URL = props.getProperty("db.url");
        DB_USER = props.getProperty("db.user");
        DB_PWD = props.getProperty("db.password");
    }

    /**
     * Methode appelee par l'API CDI pour injecter la connexion aux plats
     * @return un objet implementant PlatRepositoryInterface
     */
    @Produces
    private PlatRepositoryInterface openPlatDbConnection() {
        PlatRepositoryMariadb db = null;
        try {
            db = new PlatRepositoryMariadb(DB_URL, DB_USER, DB_PWD);
        } catch (Exception e) {
            System.err.println("ERREUR CONNEXION PLAT: " + e.getMessage());
            e.printStackTrace();
        }
        return db;
    }

    /**
     * Methode permettant de fermer la connexion plats
     * @param platRepo la connexion a fermer
     */
    private void closePlatDbConnection(@Disposes PlatRepositoryInterface platRepo) {
        platRepo.close();
    }

    /**
     * Methode appelee par l'API CDI pour injecter la connexion aux utilisateurs
     * @return un objet implementant UtilisateurRepositoryInterface
     */
    @Produces
    private UtilisateurRepositoryInterface openUtilisateurDbConnection() {
        UtilisateurRepositoryMariadb db = null;
        try {
            db = new UtilisateurRepositoryMariadb(DB_URL, DB_USER, DB_PWD);
        } catch (Exception e) {
            System.err.println("ERREUR CONNEXION UTILISATEUR: " + e.getMessage());
            e.printStackTrace();
        }
        return db;
    }

    /**
     * Methode permettant de fermer la connexion utilisateurs
     * @param utilisateurRepo la connexion a fermer
     */
    private void closeUtilisateurDbConnection(@Disposes UtilisateurRepositoryInterface utilisateurRepo) {
        utilisateurRepo.close();
    }
}
