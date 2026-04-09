package fr.univamu.iut.food;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associee aux plats
 * (point d'acces de l'API REST)
 */
@Path("/plats")
@ApplicationScoped
public class FoodResource {

    /**
     * Service utilise pour acceder aux donnees des plats et recuperer/modifier leurs informations
     */
    private FoodService service;

    /**
     * Constructeur par defaut
     */
    public FoodResource() {
    }

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'acces aux donnees
     * @param foodRepo objet implementant l'interface d'acces aux donnees
     */
    public @Inject FoodResource(FoodRepositoryInterface foodRepo) {
        this.service = new FoodService(foodRepo);
    }

    /**
     * Constructeur permettant d'initialiser le service d'acces aux plats
     */
    public FoodResource(FoodService service) {
        this.service = service;
    }

    /**
     * Endpoint permettant de publier tous les plats enregistres
     * @return la liste des plats (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllFoods() {
        return service.getAllFoodsJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un plat dont l'identifiant est passe en parametre dans le chemin
     * @param id identifiant du plat recherche
     * @return les informations du plat recherche au format JSON
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getFood(@PathParam("id") int id) {

        String result = service.getFoodJSON(id);

        // si le plat n'a pas ete trouve
        if (result == null)
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre a jour un plat
     * @param id l'identifiant du plat a mettre a jour
     * @param food le plat transmis en HTTP au format JSON et converti en objet Food
     * @return une reponse "updated" si la mise a jour a ete effectuee, une erreur NotFound sinon
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateFood(@PathParam("id") int id, Food food) {

        // si le plat n'a pas ete trouve
        if (!service.updateFood(id, food))
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }

    /**
     * Endpoint permettant de creer un nouveau plat
     * @param food le plat transmis en HTTP au format JSON et converti en objet Food
     * @return le plat cree au format JSON avec son identifiant genere, code 201 Created
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createFood(Food food) {
        String result = service.createFoodJSON(food);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    /**
     * Endpoint permettant de supprimer un plat
     * @param id l'identifiant du plat a supprimer
     * @return une reponse 204 No Content si supprime, une erreur NotFound sinon
     */
    @DELETE
    @Path("{id}")
    public Response deleteFood(@PathParam("id") int id) {
        if (!service.deleteFood(id))
            throw new NotFoundException();
        return Response.noContent().build();
    }
}
