package fr.univamu.iut.platsutilisateurs.service;

import fr.univamu.iut.platsutilisateurs.domain.Plat;
import java.util.ArrayList;

/**
 * Interface Gateway d'acces aux donnees des plats
 * Couche : Application Business Rules (Use Cases)
 * Les regles metier dependent de cette interface, jamais de l'implementation concrete (MariaDB, etc.)
 */
public interface PlatRepositoryInterface {

    void close();

    Plat getPlat(int id);

    ArrayList<Plat> getAllPlats();

    Plat createPlat(String nom, String description, double prix);

    boolean updatePlat(int id, String nom, String description, double prix);

    boolean deletePlat(int id);
}
