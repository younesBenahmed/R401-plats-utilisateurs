package fr.univamu.iut.platsutilisateurs.service;

import fr.univamu.iut.platsutilisateurs.domain.Utilisateur;

/**
 * Port d'entree du cas d'utilisation Utilisateur (Use Case Input Port)
 * Couche : Application Business Rules
 * Le Controller (ui) depend de cette interface, jamais de l'interactor concret
 */
public interface UtilisateurUseCaseInterface {

    String getAllUtilisateursJSON();

    String getUtilisateurJSON(int id);

    Utilisateur createUtilisateur(Utilisateur utilisateur);

    String updateUtilisateur(int id, Utilisateur utilisateur);

    boolean deleteUtilisateur(int id);
}
