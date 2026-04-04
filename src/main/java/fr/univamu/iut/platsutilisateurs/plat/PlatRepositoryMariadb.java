package fr.univamu.iut.platsutilisateurs.plat;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'acceder aux plats stockes dans une base de donnees MariaDB
 */
public class PlatRepositoryMariadb implements PlatRepositoryInterface, Closeable {

    /**
     * Acces a la base de donnees (session)
     */
    protected Connection dbConnection;

    /**
     * Constructeur de la classe
     * @param infoConnection chaine de caracteres avec les informations de connexion
     * @param user chaine de caracteres contenant l'identifiant de connexion
     * @param pwd chaine de caracteres contenant le mot de passe
     */
    public PlatRepositoryMariadb(String infoConnection, String user, String pwd)
            throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Plat getPlat(int id) {
        Plat selectedPlat = null;
        String query = "SELECT * FROM Plat WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");
                selectedPlat = new Plat(id, nom, description, prix);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedPlat;
    }

    @Override
    public ArrayList<Plat> getAllPlats() {
        ArrayList<Plat> listPlats;
        String query = "SELECT * FROM Plat";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            listPlats = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");
                listPlats.add(new Plat(id, nom, description, prix));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listPlats;
    }

    @Override
    public Plat createPlat(String nom, String description, double prix) {
        String query = "INSERT INTO Plat (nom, description, prix) VALUES (?, ?, ?)";
        Plat createdPlat = null;

        try (PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nom);
            ps.setString(2, description);
            ps.setDouble(3, prix);
            int nbRowModified = ps.executeUpdate();

            if (nbRowModified != 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    createdPlat = new Plat(generatedId, nom, description, prix);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return createdPlat;
    }

    @Override
    public boolean updatePlat(int id, String nom, String description, double prix) {
        String query = "UPDATE Plat SET nom=?, description=?, prix=? WHERE id=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setString(2, description);
            ps.setDouble(3, prix);
            ps.setInt(4, id);
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    @Override
    public boolean deletePlat(int id) {
        String query = "DELETE FROM Plat WHERE id=?";
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
