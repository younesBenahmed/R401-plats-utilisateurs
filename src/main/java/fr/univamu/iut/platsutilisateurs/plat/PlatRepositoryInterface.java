package fr.univamu.iut.platsutilisateurs.plat;

import java.util.ArrayList;

/**
 * Interface d'acces aux donnees des plats
 */
public interface PlatRepositoryInterface {

    /**
     * Methode fermant le depot ou sont stockees les informations sur les plats
     */
    public void close();

    /**
     * Methode retournant le plat dont l'identifiant est passe en parametre
     * @param id identifiant du plat recherche
     * @return un objet Plat representant le plat recherche
     */
    public Plat getPlat(int id);

    /**
     * Methode retournant la liste des plats
     * @return une liste d'objets Plat
     */
    public ArrayList<Plat> getAllPlats();

    /**
     * Methode permettant de creer un plat
     * @param nom nom du plat
     * @param description description du plat
     * @param prix prix du plat
     * @return un objet Plat avec l'identifiant genere, ou null si la creation a echoue
     */
    public Plat createPlat(String nom, String description, double prix);

    /**
     * Methode permettant de mettre a jour un plat enregistre
     * @param id identifiant du plat a mettre a jour
     * @param nom nouveau nom du plat
     * @param description nouvelle description du plat
     * @param prix nouveau prix du plat
     * @return true si le plat existe et la mise a jour a ete faite, false sinon
     */
    public boolean updatePlat(int id, String nom, String description, double prix);

    /**
     * Methode permettant de supprimer un plat
     * @param id identifiant du plat a supprimer
     * @return true si le plat a ete supprime, false sinon
     */
    public boolean deletePlat(int id);
}
