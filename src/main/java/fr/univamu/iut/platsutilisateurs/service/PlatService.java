package fr.univamu.iut.platsutilisateurs.service;

import fr.univamu.iut.platsutilisateurs.domain.Plat;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Interactor du cas d'utilisation Plat
 * Couche : Application Business Rules
 * Implemente le Use Case Input Port (PlatUseCaseInterface)
 * Utilise le Gateway Interface (PlatRepositoryInterface) pour acceder aux donnees
 */
public class PlatService implements PlatUseCaseInterface {

    protected PlatRepositoryInterface platRepo;

    public PlatService(PlatRepositoryInterface platRepo) {
        this.platRepo = platRepo;
    }

    @Override
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

    @Override
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

    @Override
    public Plat createPlat(Plat plat) {
        return platRepo.createPlat(plat.nom, plat.description, plat.prix);
    }

    @Override
    public String updatePlat(int id, Plat plat) {
        boolean updated = platRepo.updatePlat(id, plat.nom, plat.description, plat.prix);
        if (!updated) return null;
        return getPlatJSON(id);
    }

    @Override
    public boolean deletePlat(int id) {
        return platRepo.deletePlat(id);
    }
}
