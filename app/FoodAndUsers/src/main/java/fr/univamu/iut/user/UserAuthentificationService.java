package fr.univamu.iut.user;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 * Service d'authentification des utilisateurs.
 * Verifie qu'un utilisateur existe dans la base de donnees a partir de son email.
 */
public class UserAuthentificationService {

    /**
     * Objet permettant d'acceder au depot des utilisateurs
     */
    protected UserRepositoryInterface userRepo;

    /**
     * Constructeur permettant d'injecter l'acces aux donnees
     * @param userRepo objet implementant l'interface d'acces aux donnees
     */
    public UserAuthentificationService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Methode retournant les informations d'un utilisateur identifie par son email, au format JSON.
     * Retourne null si aucun utilisateur ne correspond a cet email.
     * @param email l'adresse email a verifier
     * @return les informations de l'utilisateur au format JSON, ou null s'il n'existe pas
     */
    public String getUserByEmailJSON(String email) {
        String result = null;
        User user = userRepo.getUserByEmail(email);

        if (user != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(user);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }
}
