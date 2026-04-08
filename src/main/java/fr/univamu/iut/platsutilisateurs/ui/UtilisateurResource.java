package fr.univamu.iut.platsutilisateurs.ui;

import fr.univamu.iut.platsutilisateurs.domain.Utilisateur;
import fr.univamu.iut.platsutilisateurs.service.UtilisateurUseCaseInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.net.URI;

/**
 * Controller REST associe aux utilisateurs (point d'acces de l'API)
 * Couche : Interface Adapters
 * Depend uniquement du Use Case Input Port (UtilisateurUseCaseInterface), jamais de l'interactor concret
 */
@Path("/utilisateurs")
@ApplicationScoped
public class UtilisateurResource {

    private UtilisateurUseCaseInterface service;

    public UtilisateurResource() {}

    public @Inject UtilisateurResource(UtilisateurUseCaseInterface service) {
        this.service = service;
    }

    @GET
    @Produces("application/json")
    public String getAllUtilisateurs() {
        return service.getAllUtilisateursJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUtilisateur(@PathParam("id") int id) {
        String result = service.getUtilisateurJSON(id);
        if (result == null) throw new NotFoundException();
        return result;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUtilisateur(Utilisateur utilisateur) {
        Utilisateur created = service.createUtilisateur(utilisateur);
        if (created == null) throw new ServerErrorException(Response.Status.INTERNAL_SERVER_ERROR);
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(created);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Response.status(Response.Status.CREATED)
                .entity(result)
                .location(URI.create("/utilisateurs/" + created.getId()))
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUtilisateur(@PathParam("id") int id, Utilisateur utilisateur) {
        String result = service.updateUtilisateur(id, utilisateur);
        if (result == null) throw new NotFoundException();
        return Response.ok(result).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUtilisateur(@PathParam("id") int id) {
        if (!service.deleteUtilisateur(id)) throw new NotFoundException();
        return Response.noContent().build();
    }
}
