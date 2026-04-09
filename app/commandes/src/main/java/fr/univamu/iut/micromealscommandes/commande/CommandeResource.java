package fr.univamu.iut.micromealscommandes.commande;

import fr.univamu.iut.micromealscommandes.menu.MenuRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource REST exposant les endpoints de gestion des commandes
 */
@Path("/commandes")
@ApplicationScoped
public class CommandeResource {

    private CommandeService service;

    public CommandeResource() {
    }

    public @Inject CommandeResource(CommandeRepositoryInterface commandeRepo, MenuRepositoryInterface menuRepo) {
        this.service = new CommandeService(commandeRepo, menuRepo);
    }

    /**
     * Retourne toutes les commandes, ou celles d'un abonne si {@code abonneId} est fourni
     *
     * @param abonneId identifiant optionnel de l'abonne
     * @return les commandes au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllCommandes(@QueryParam("abonneId") Integer abonneId) {
        if (abonneId != null)
            return service.getCommandesByAbonneIdJSON(abonneId);

        return service.getAllCommandesJSON();
    }

    /**
     * Retourne une commande par son identifiant
     *
     * @param id identifiant de la commande
     * @return la commande au format JSON
     * @throws NotFoundException si la commande est introuvable
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getCommande(@PathParam("id") int id) {

        String result = service.getCommandeJSON(id);

        if (result == null)
            throw new NotFoundException();

        return result;
    }

    /**
     * Cree une nouvelle commande
     *
     * @param input donnees de la commande a creer
     * @return la commande creee au format JSON
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createCommande(CommandeInput input) {

        String result = service.createCommande(input);

        if (result == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    /**
     * Met a jour une commande existante
     *
     * @param id    identifiant de la commande
     * @param input nouvelles donnees de livraison
     * @return la commande mise à jour au format JSON
     * @throws NotFoundException si la commande est introuvable
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public String updateCommande(@PathParam("id") int id, CommandeUpdateInput input) {

        String result = service.updateCommande(id, input);

        if (result == null)
            throw new NotFoundException();

        return result;
    }

    /**
     * Supprime une commande par son identifiant.
     *
     * @param id identifiant de la commande
     * @return reponse 204 si la suppression a reussi
     * @throws NotFoundException si la commande est introuvable
     */
    @DELETE
    @Path("{id}")
    public Response deleteCommande(@PathParam("id") int id) {

        if (!service.deleteCommande(id))
            throw new NotFoundException();

        return Response.noContent().build();
    }
}
