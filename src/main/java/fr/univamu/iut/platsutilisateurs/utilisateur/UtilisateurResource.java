package fr.univamu.iut.platsutilisateurs.utilisateur;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;

/**
 * Ressource associee aux utilisateurs
 * (point d'acces de l'API REST)
 */
@Path("/utilisateurs")
@ApplicationScoped
public class UtilisateurResource {

    /**
     * Service utilise pour acceder aux donnees des utilisateurs
     */
    private UtilisateurService service;

    /**
     * Constructeur par defaut
     */
    public UtilisateurResource() {}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'acces aux donnees
     * @param utilisateurRepo objet implementant l'interface d'acces aux donnees
     */
    public @Inject UtilisateurResource(UtilisateurRepositoryInterface utilisateurRepo) {
        this.service = new UtilisateurService(utilisateurRepo);
    }

    /**
     * Endpoint permettant de publier tous les utilisateurs enregistres
     * @return la liste des utilisateurs au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllUtilisateurs() {
        return service.getAllUtilisateursJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un utilisateur
     * @param id identifiant de l'utilisateur recherche
     * @return les informations de l'utilisateur au format JSON
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUtilisateur(@PathParam("id") int id) {
        String result = service.getUtilisateurJSON(id);
        if (result == null)
            throw new NotFoundException();
        return result;
    }

    /**
     * Endpoint permettant de creer un utilisateur
     * @param utilisateur l'utilisateur transmis en HTTP au format JSON
     * @return une reponse 201 avec l'utilisateur cree au format JSON
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUtilisateur(Utilisateur utilisateur) {
        Utilisateur created = service.createUtilisateur(utilisateur);
        if (created == null)
            throw new ServerErrorException(Response.Status.INTERNAL_SERVER_ERROR);

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

    /**
     * Endpoint permettant de mettre a jour un utilisateur
     * @param id l'identifiant de l'utilisateur a modifier
     * @param utilisateur l'utilisateur transmis en HTTP au format JSON
     * @return une reponse 200 avec l'utilisateur mis a jour au format JSON
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUtilisateur(@PathParam("id") int id, Utilisateur utilisateur) {
        String result = service.updateUtilisateur(id, utilisateur);
        if (result == null)
            throw new NotFoundException();
        return Response.ok(result).build();
    }

    /**
     * Endpoint permettant de supprimer un utilisateur
     * @param id l'identifiant de l'utilisateur a supprimer
     * @return une reponse 204 si la suppression a ete effectuee
     */
    @DELETE
    @Path("{id}")
    public Response deleteUtilisateur(@PathParam("id") int id) {
        if (!service.deleteUtilisateur(id))
            throw new NotFoundException();
        return Response.noContent().build();
    }
}
