package fr.univamu.iut.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.util.Base64;

/**
 * Ressource associee a l'authentification des utilisateurs.
 * Expose le endpoint GET /utilisateurs/connexion avec HTTP Basic Auth.
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
     * Endpoint permettant d'authentifier un utilisateur via HTTP Basic Auth.
     * L'identifiant utilise est l'adresse email.
     *
     * Exemple : curl -u email:password -X GET http://localhost:3003/api/utilisateurs/connexion
     *
     * @param requestContext contexte de la requete HTTP (pour lire le header Authorization)
     * @return true si l'authentification reussit, false sinon, 401 si le header est absent
     */
    @GET
    @Produces("text/plain")
    public Response authenticate(@Context ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic")) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .header("WWW-Authenticate", "Basic realm=\"utilisateurs\"")
                    .build();
        }

        String decoded = new String(Base64.getDecoder().decode(authHeader.split(" ")[1]));
        String[] tokens = decoded.split(":", 2);

        if (tokens.length != 2) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .header("WWW-Authenticate", "Basic realm=\"utilisateurs\"")
                    .build();
        }

        String email = tokens[0];
        String password = tokens[1];

        boolean result = authService.isValidUser(email, password);
        return Response.ok(String.valueOf(result)).build();
    }
}
