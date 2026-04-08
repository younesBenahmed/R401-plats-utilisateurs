package fr.univamu.iut.platsutilisateurs.database;

import fr.univamu.iut.platsutilisateurs.domain.Utilisateur;
import fr.univamu.iut.platsutilisateurs.service.UtilisateurRepositoryInterface;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Implementation du Gateway d'acces aux utilisateurs via MariaDB
 * Couche : Frameworks and Drivers (couche externe)
 * Implemente UtilisateurRepositoryInterface (defini dans la couche service)
 * La connexion SQL est partagee et injectee par CDI (PlatsUtilisateursApplication)
 */
public class UtilisateurRepositoryMariadb implements UtilisateurRepositoryInterface, Closeable {

    protected Connection dbConnection;

    public UtilisateurRepositoryMariadb(Connection connection) {
        this.dbConnection = connection;
    }

    @Override
    public void close() {
        // La connexion est geree par CDI (PlatsUtilisateursApplication)
    }

    @Override
    public Utilisateur getUtilisateur(int id) {
        Utilisateur selectedUtilisateur = null;
        String query = "SELECT * FROM Utilisateur WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String email = result.getString("email");
                String adresse = result.getString("adresse");
                selectedUtilisateur = new Utilisateur(id, nom, prenom, email, adresse);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUtilisateur;
    }

    @Override
    public ArrayList<Utilisateur> getAllUtilisateurs() {
        ArrayList<Utilisateur> listUtilisateurs;
        String query = "SELECT * FROM Utilisateur";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            listUtilisateurs = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String email = result.getString("email");
                String adresse = result.getString("adresse");
                listUtilisateurs.add(new Utilisateur(id, nom, prenom, email, adresse));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUtilisateurs;
    }

    @Override
    public Utilisateur createUtilisateur(String nom, String prenom, String email, String adresse) {
        String query = "INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES (?, ?, ?, ?)";
        Utilisateur createdUtilisateur = null;
        try (PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setString(4, adresse);
            int nbRowModified = ps.executeUpdate();
            if (nbRowModified != 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    createdUtilisateur = new Utilisateur(generatedKeys.getInt(1), nom, prenom, email, adresse);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return createdUtilisateur;
    }

    @Override
    public boolean updateUtilisateur(int id, String nom, String prenom, String email, String adresse) {
        String query = "UPDATE Utilisateur SET nom=?, prenom=?, email=?, adresse=? WHERE id=?";
        int nbRowModified = 0;
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setString(4, adresse);
            ps.setInt(5, id);
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    @Override
    public boolean deleteUtilisateur(int id) {
        String query = "DELETE FROM Utilisateur WHERE id=?";
        int nbRowModified = 0;
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }
}
