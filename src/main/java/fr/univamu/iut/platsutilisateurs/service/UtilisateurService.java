package fr.univamu.iut.platsutilisateurs.service;

import fr.univamu.iut.platsutilisateurs.domain.Utilisateur;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Interactor du cas d'utilisation Utilisateur
 * Couche : Application Business Rules
 * Implemente le Use Case Input Port (UtilisateurUseCaseInterface)
 * Utilise le Gateway Interface (UtilisateurRepositoryInterface) pour acceder aux donnees
 */
public class UtilisateurService implements UtilisateurUseCaseInterface {

    protected UtilisateurRepositoryInterface utilisateurRepo;

    public UtilisateurService(UtilisateurRepositoryInterface utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    @Override
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

    @Override
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

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepo.createUtilisateur(utilisateur.nom, utilisateur.prenom,
                utilisateur.email, utilisateur.adresse);
    }

    @Override
    public String updateUtilisateur(int id, Utilisateur utilisateur) {
        boolean updated = utilisateurRepo.updateUtilisateur(id, utilisateur.nom, utilisateur.prenom,
                utilisateur.email, utilisateur.adresse);
        if (!updated) return null;
        return getUtilisateurJSON(id);
    }

    @Override
    public boolean deleteUtilisateur(int id) {
        return utilisateurRepo.deleteUtilisateur(id);
    }
}
