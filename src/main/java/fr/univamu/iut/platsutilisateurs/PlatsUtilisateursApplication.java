package fr.univamu.iut.platsutilisateurs;

import fr.univamu.iut.platsutilisateurs.database.PlatRepositoryMariadb;
import fr.univamu.iut.platsutilisateurs.database.UtilisateurRepositoryMariadb;
import fr.univamu.iut.platsutilisateurs.service.PlatRepositoryInterface;
import fr.univamu.iut.platsutilisateurs.service.PlatService;
import fr.univamu.iut.platsutilisateurs.service.PlatUseCaseInterface;
import fr.univamu.iut.platsutilisateurs.service.UtilisateurRepositoryInterface;
import fr.univamu.iut.platsutilisateurs.service.UtilisateurService;
import fr.univamu.iut.platsutilisateurs.service.UtilisateurUseCaseInterface;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Composant principal Main de l'application
 * Couche : Frameworks and Drivers (couche la plus externe)
 * Lance et coordonne l'execution des autres composants via CDI
 * Cree les objets necessaires et les injecte dans les composants de haut niveau
 */
@ApplicationPath("/api")
@ApplicationScoped
public class PlatsUtilisateursApplication extends Application {

    private static final String DB_URL;
    private static final String DB_USER;
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
     * Produit la connexion SQL unique partagee entre les deux repositories
     */
    @Produces
    @ApplicationScoped
    private Connection openDbConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        } catch (Exception e) {
            System.err.println("ERREUR CONNEXION: " + e.getMessage());
            return null;
        }
    }

    /**
     * Ferme la connexion SQL quand l'application s'arrete
     */
    private void closeDbConnection(@Disposes Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Produit le Gateway Plat (couche database) en injectant la connexion partagee
     */
    @Produces
    private PlatRepositoryInterface getPlatRepository(Connection connection) {
        return new PlatRepositoryMariadb(connection);
    }

    /**
     * Produit le Gateway Utilisateur (couche database) en injectant la connexion partagee
     */
    @Produces
    private UtilisateurRepositoryInterface getUtilisateurRepository(Connection connection) {
        return new UtilisateurRepositoryMariadb(connection);
    }

    /**
     * Produit l'Interactor Plat via son Use Case Input Port
     */
    @Produces
    private PlatUseCaseInterface getPlatService(PlatRepositoryInterface platRepo) {
        return new PlatService(platRepo);
    }

    /**
     * Produit l'Interactor Utilisateur via son Use Case Input Port
     */
    @Produces
    private UtilisateurUseCaseInterface getUtilisateurService(UtilisateurRepositoryInterface utilisateurRepo) {
        return new UtilisateurService(utilisateurRepo);
    }
}
