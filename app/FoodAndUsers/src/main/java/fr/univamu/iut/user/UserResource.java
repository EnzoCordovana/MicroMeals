package fr.univamu.iut.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associee aux utilisateurs
 * (point d'acces de l'API REST)
 */
@Path("/utilisateurs")
@ApplicationScoped
public class UserResource {

    /**
     * Service utilise pour acceder aux donnees des utilisateurs et recuperer/modifier leurs informations
     */
    private UserService service;

    /**
     * Constructeur par defaut
     */
    public UserResource() {
    }

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'acces aux donnees
     * @param userRepo objet implementant l'interface d'acces aux donnees
     */
    public @Inject UserResource(UserRepositoryInterface userRepo) {
        this.service = new UserService(userRepo);
    }

    /**
     * Constructeur permettant d'initialiser le service d'acces aux utilisateurs
     */
    public UserResource(UserService service) {
        this.service = service;
    }

    /**
     * Endpoint permettant de publier tous les utilisateurs enregistres
     * @return la liste des utilisateurs au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un utilisateur dont l'identifiant est passe dans le chemin
     * @param id identifiant de l'utilisateur recherche
     * @return les informations de l'utilisateur au format JSON
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") int id) {
        String result = service.getUserJSON(id);

        if (result == null)
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre a jour un utilisateur
     * @param id l'identifiant de l'utilisateur a mettre a jour
     * @param user l'utilisateur transmis en HTTP au format JSON et converti en objet User
     * @return une reponse "updated" si la mise a jour a ete effectuee, une erreur NotFound sinon
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateUser(@PathParam("id") int id, User user) {
        if (!service.updateUser(id, user))
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }

    /**
     * Endpoint permettant de creer un nouvel utilisateur
     * @param user l'utilisateur transmis en HTTP au format JSON et converti en objet User
     * @return l'utilisateur cree au format JSON avec son identifiant genere, code 201 Created
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createUser(User user) {
        String result = service.createUserJSON(user);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    /**
     * Endpoint permettant de supprimer un utilisateur
     * @param id l'identifiant de l'utilisateur a supprimer
     * @return une reponse 204 No Content si supprime, une erreur NotFound sinon
     */
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        if (!service.deleteUser(id))
            throw new NotFoundException();
        return Response.noContent().build();
    }
}
