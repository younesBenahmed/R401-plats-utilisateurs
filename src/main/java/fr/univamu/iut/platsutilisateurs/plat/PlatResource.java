package fr.univamu.iut.platsutilisateurs.plat;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.net.URI;

/**
 * Ressource associee aux plats
 * (point d'acces de l'API REST)
 */
@Path("/plats")
@ApplicationScoped
public class PlatResource {

    /**
     * Service utilise pour acceder aux donnees des plats
     */
    private PlatService service;

    /**
     * Constructeur par defaut
     */
    public PlatResource() {}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'acces aux donnees
     * @param platRepo objet implementant l'interface d'acces aux donnees
     */
    public @Inject PlatResource(PlatRepositoryInterface platRepo) {
        this.service = new PlatService(platRepo);
    }

    /**
     * Endpoint permettant de publier tous les plats enregistres
     * @return la liste des plats au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatsJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un plat
     * @param id identifiant du plat recherche
     * @return les informations du plat au format JSON
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getPlat(@PathParam("id") int id) {
        String result = service.getPlatJSON(id);
        if (result == null)
            throw new NotFoundException();
        return result;
    }

    /**
     * Endpoint permettant de creer un plat
     * @param plat le plat transmis en HTTP au format JSON
     * @return une reponse avec le statut 201 et le plat cree au format JSON
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createPlat(Plat plat) {
        Plat createdPlat = service.createPlat(plat);
        if (createdPlat == null)
            throw new ServerErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        return Response.created(URI.create("/plats/" + createdPlat.getId())).entity(createdPlat).build();
    }

    /**
     * Endpoint permettant de mettre a jour un plat
     * @param id l'identifiant du plat a modifier
     * @param plat le plat transmis en HTTP au format JSON
     * @return une reponse avec le statut 200 et le plat mis a jour au format JSON
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePlat(@PathParam("id") int id, Plat plat) {
        String result = service.updatePlat(id, plat);
        if (result == null)
            throw new NotFoundException();
        return Response.ok(result).build();
    }

    /**
     * Endpoint permettant de supprimer un plat
     * @param id l'identifiant du plat a supprimer
     * @return une reponse avec le statut 204 si la suppression a ete effectuee
     */
    @DELETE
    @Path("{id}")
    public Response deletePlat(@PathParam("id") int id) {
        if (!service.deletePlat(id))
            throw new NotFoundException();
        return Response.noContent().build();
    }
}
