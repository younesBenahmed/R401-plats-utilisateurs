package fr.univamu.iut.platsutilisateurs.service;

import fr.univamu.iut.platsutilisateurs.domain.Utilisateur;
import java.util.ArrayList;

/**
 * Interface Gateway d'acces aux donnees des utilisateurs
 * Couche : Application Business Rules (Use Cases)
 * Les regles metier dependent de cette interface, jamais de l'implementation concrete (MariaDB, etc.)
 */
public interface UtilisateurRepositoryInterface {

    void close();

    Utilisateur getUtilisateur(int id);

    ArrayList<Utilisateur> getAllUtilisateurs();

    Utilisateur createUtilisateur(String nom, String prenom, String email, String adresse);

    boolean updateUtilisateur(int id, String nom, String prenom, String email, String adresse);

    boolean deleteUtilisateur(int id);
}
