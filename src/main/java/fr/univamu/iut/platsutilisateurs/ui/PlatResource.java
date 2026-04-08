package fr.univamu.iut.platsutilisateurs.ui;

import fr.univamu.iut.platsutilisateurs.domain.Plat;
import fr.univamu.iut.platsutilisateurs.service.PlatUseCaseInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.net.URI;

/**
 * Controller REST associe aux plats (point d'acces de l'API)
 * Couche : Interface Adapters
 * Depend uniquement du Use Case Input Port (PlatUseCaseInterface), jamais de l'interactor concret
 */
@Path("/plats")
@ApplicationScoped
public class PlatResource {

    private PlatUseCaseInterface service;

    public PlatResource() {}

    public @Inject PlatResource(PlatUseCaseInterface service) {
        this.service = service;
    }

    @GET
    @Produces("application/json")
    public String getAllPlats() {
        return service.getAllPlatsJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getPlat(@PathParam("id") int id) {
        String result = service.getPlatJSON(id);
        if (result == null) throw new NotFoundException();
        return result;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createPlat(Plat plat) {
        Plat createdPlat = service.createPlat(plat);
        if (createdPlat == null) throw new ServerErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        return Response.created(URI.create("/plats/" + createdPlat.getId())).entity(createdPlat).build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePlat(@PathParam("id") int id, Plat plat) {
        String result = service.updatePlat(id, plat);
        if (result == null) throw new NotFoundException();
        return Response.ok(result).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePlat(@PathParam("id") int id) {
        if (!service.deletePlat(id)) throw new NotFoundException();
        return Response.noContent().build();
    }
}
