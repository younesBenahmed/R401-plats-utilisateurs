package fr.univamu.iut.platsutilisateurs.plat;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisee pour recuperer les informations necessaires a la ressource
 * (permet de dissocier ressource et mode d'acces aux donnees)
 */
public class PlatService {

    /**
     * Objet permettant d'acceder au depot ou sont stockees les informations sur les plats
     */
    protected PlatRepositoryInterface platRepo;

    /**
     * Constructeur permettant d'injecter l'acces aux donnees
     * @param platRepo objet implementant l'interface d'acces aux donnees
     */
    public PlatService(PlatRepositoryInterface platRepo) {
        this.platRepo = platRepo;
    }

    /**
     * Methode retournant les informations sur les plats au format JSON
     * @return une chaine de caracteres contenant les informations au format JSON
     */
    public String getAllPlatsJSON() {
        ArrayList<Plat> allPlats = platRepo.getAllPlats();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allPlats);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Methode retournant au format JSON les informations sur un plat recherche
     * @param id l'identifiant du plat recherche
     * @return une chaine de caracteres contenant les informations au format JSON
     */
    public String getPlatJSON(int id) {
        String result = null;
        Plat myPlat = platRepo.getPlat(id);

        if (myPlat != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myPlat);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Methode permettant de creer un plat
     * @param plat le plat a creer
     * @return le plat cree avec son identifiant genere, ou null si la creation a echoue
     */
    public Plat createPlat(Plat plat) {
        return platRepo.createPlat(plat.nom, plat.description, plat.prix);
    }

    /**
     * Methode permettant de mettre a jour les informations d'un plat
     * @param id identifiant du plat a mettre a jour
     * @param plat les nouvelles informations a utiliser
     * @return une chaine de caracteres contenant les informations du plat mis a jour au format JSON, ou null si le plat n'existe pas
     */
    public String updatePlat(int id, Plat plat) {
        boolean updated = platRepo.updatePlat(id, plat.nom, plat.description, plat.prix);
        if (!updated) {
            return null;
        }
        return getPlatJSON(id);
    }

    /**
     * Methode permettant de supprimer un plat
     * @param id identifiant du plat a supprimer
     * @return true si le plat a pu etre supprime
     */
    public boolean deletePlat(int id) {
        return platRepo.deletePlat(id);
    }
}
