package fr.univamu.iut.micromealscommandes.commande;

import fr.univamu.iut.micromealscommandes.menu.MenuRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/commandes")
@ApplicationScoped
public class CommandeResource {

    private CommandeService service;

    public CommandeResource() {
    }

    public @Inject CommandeResource(CommandeRepositoryInterface commandeRepo, MenuRepositoryInterface menuRepo) {
        this.service = new CommandeService(commandeRepo, menuRepo);
    }

    @GET
    @Produces("application/json")
    public String getAllCommandes(@QueryParam("abonneId") Integer abonneId) {
        if (abonneId != null)
            return service.getCommandesByAbonneIdJSON(abonneId);

        return service.getAllCommandesJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getCommande(@PathParam("id") int id) {

        String result = service.getCommandeJSON(id);

        if (result == null)
            throw new NotFoundException();

        return result;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createCommande(CommandeInput input) {

        String result = service.createCommande(input);

        if (result == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.status(Response.Status.CREATED).entity(result).build();
    }

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

    @DELETE
    @Path("{id}")
    public Response deleteCommande(@PathParam("id") int id) {

        if (!service.deleteCommande(id))
            throw new NotFoundException();

        return Response.noContent().build();
    }
}
