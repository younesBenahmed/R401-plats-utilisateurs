package fr.univamu.iut.platsutilisateurs.service;

import fr.univamu.iut.platsutilisateurs.domain.Plat;

/**
 * Port d'entree du cas d'utilisation Plat (Use Case Input Port)
 * Couche : Application Business Rules
 * Le Controller (ui) depend de cette interface, jamais de l'interactor concret
 */
public interface PlatUseCaseInterface {

    String getAllPlatsJSON();

    String getPlatJSON(int id);

    Plat createPlat(Plat plat);

    String updatePlat(int id, Plat plat);

    boolean deletePlat(int id);
}
