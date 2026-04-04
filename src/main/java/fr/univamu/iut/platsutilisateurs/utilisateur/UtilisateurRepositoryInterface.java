package fr.univamu.iut.platsutilisateurs.utilisateur;

import java.util.ArrayList;

/**
 * Interface d'acces aux donnees des utilisateurs
 */
public interface UtilisateurRepositoryInterface {

    /**
     * Methode fermant le depot ou sont stockees les informations sur les utilisateurs
     */
    public void close();

    /**
     * Methode retournant l'utilisateur dont l'identifiant est passe en parametre
     * @param id identifiant de l'utilisateur recherche
     * @return un objet Utilisateur representant l'utilisateur recherche
     */
    public Utilisateur getUtilisateur(int id);

    /**
     * Methode retournant la liste des utilisateurs
     * @return une liste d'objets Utilisateur
     */
    public ArrayList<Utilisateur> getAllUtilisateurs();

    /**
     * Methode permettant de creer un utilisateur
     * @param nom nom de l'utilisateur
     * @param prenom prenom de l'utilisateur
     * @param email email de l'utilisateur
     * @param adresse adresse de l'utilisateur
     * @return l'utilisateur cree avec son identifiant genere, ou null si la creation a echoue
     */
    public Utilisateur createUtilisateur(String nom, String prenom, String email, String adresse);

    /**
     * Methode permettant de mettre a jour un utilisateur
     * @param id identifiant de l'utilisateur a mettre a jour
     * @param nom nouveau nom
     * @param prenom nouveau prenom
     * @param email nouvel email
     * @param adresse nouvelle adresse
     * @return true si l'utilisateur existe et la mise a jour a ete faite, false sinon
     */
    public boolean updateUtilisateur(int id, String nom, String prenom, String email, String adresse);

    /**
     * Methode permettant de supprimer un utilisateur
     * @param id identifiant de l'utilisateur a supprimer
     * @return true si l'utilisateur a ete supprime, false sinon
     */
    public boolean deleteUtilisateur(int id);
}
