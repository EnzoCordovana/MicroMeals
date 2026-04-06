package fr.univamu.iut.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associee a l'authentification des utilisateurs.
 * Expose le endpoint GET /utilisateurs/connexion?email=...
 *
 * Ce chemin est plus specifique que /utilisateurs/{id}, donc JAX-RS
 * le resoudra en priorite (un chemin litteral prend precedence sur un template).
 */
@Path("/utilisateurs/connexion")
@ApplicationScoped
public class UserAuthentificationRessource {

    /**
     * Service d'authentification
     */
    private UserAuthentificationService authService;

    /**
     * Constructeur par defaut
     */
    public UserAuthentificationRessource() {
    }

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'acces aux donnees
     * @param userRepo objet implementant l'interface d'acces aux donnees
     */
    public @Inject UserAuthentificationRessource(UserRepositoryInterface userRepo) {
        this.authService = new UserAuthentificationService(userRepo);
    }

    /**
     * Endpoint permettant de retrouver un utilisateur par son email.
     * Exemple : GET /api/utilisateurs/connexion?email=marie.dupont@email.fr
     *
     * @param email l'adresse email de l'utilisateur a rechercher
     * @return les informations de l'utilisateur au format JSON (200 OK),
     *         400 si le parametre email est absent,
     *         404 si aucun utilisateur ne correspond
     */
    @GET
    @Produces("application/json")
    public Response authenticate(@QueryParam("email") String email) {
        if (email == null || email.isBlank())
            throw new BadRequestException("Le parametre 'email' est requis");

        String result = authService.getUserByEmailJSON(email);

        if (result == null)
            throw new NotFoundException("Aucun utilisateur avec l'email : " + email);

        return Response.ok(result).build();
    }
}
