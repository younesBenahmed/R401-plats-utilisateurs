package fr.univamu.iut.platsutilisateurs.utilisateur;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisee pour recuperer les informations necessaires a la ressource
 * (permet de dissocier ressource et mode d'acces aux donnees)
 */
public class UtilisateurService {

    /**
     * Objet permettant d'acceder au depot ou sont stockees les informations sur les utilisateurs
     */
    protected UtilisateurRepositoryInterface utilisateurRepo;

    /**
     * Constructeur permettant d'injecter l'acces aux donnees
     * @param utilisateurRepo objet implementant l'interface d'acces aux donnees
     */
    public UtilisateurService(UtilisateurRepositoryInterface utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    /**
     * Methode retournant les informations sur les utilisateurs au format JSON
     * @return une chaine de caracteres contenant les informations au format JSON
     */
    public String getAllUtilisateursJSON() {
        ArrayList<Utilisateur> allUtilisateurs = utilisateurRepo.getAllUtilisateurs();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUtilisateurs);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Methode retournant au format JSON les informations sur un utilisateur recherche
     * @param id l'identifiant de l'utilisateur recherche
     * @return une chaine de caracteres contenant les informations au format JSON
     */
    public String getUtilisateurJSON(int id) {
        String result = null;
        Utilisateur myUtilisateur = utilisateurRepo.getUtilisateur(id);

        if (myUtilisateur != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUtilisateur);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Methode permettant de creer un utilisateur
     * @param utilisateur l'utilisateur a creer
     * @return l'utilisateur cree avec son identifiant genere, ou null si la creation a echoue
     */
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepo.createUtilisateur(utilisateur.nom,
                utilisateur.prenom, utilisateur.email, utilisateur.adresse);
    }

    /**
     * Methode permettant de mettre a jour les informations d'un utilisateur
     * @param id identifiant de l'utilisateur a mettre a jour
     * @param utilisateur les nouvelles informations a utiliser
     * @return une chaine de caracteres au format JSON de l'utilisateur mis a jour, ou null si non trouve
     */
    public String updateUtilisateur(int id, Utilisateur utilisateur) {
        boolean updated = utilisateurRepo.updateUtilisateur(id, utilisateur.nom, utilisateur.prenom,
                utilisateur.email, utilisateur.adresse);

        if (!updated) {
            return null;
        }

        return getUtilisateurJSON(id);
    }

    /**
     * Methode permettant de supprimer un utilisateur
     * @param id identifiant de l'utilisateur a supprimer
     * @return true si l'utilisateur a pu etre supprime
     */
    public boolean deleteUtilisateur(int id) {
        return utilisateurRepo.deleteUtilisateur(id);
    }
}
