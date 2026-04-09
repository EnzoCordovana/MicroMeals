package fr.univamu.iut.user;

/**
 * Service d'authentification des utilisateurs.
 * Verifie qu'un utilisateur existe et que son mot de passe est correct.
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
     * Methode permettant d'authentifier un utilisateur par son email et son mot de passe
     * @param email l'adresse email de l'utilisateur
     * @param password le mot de passe a verifier
     * @return true si l'utilisateur existe et que le mot de passe est correct, false sinon
     */
    public boolean isValidUser(String email, String password) {
        User user = userRepo.getUserByEmail(email);

        if (user == null)
            return false;

        if (!user.getPassword().equals(password))
            return false;

        return true;
    }
}
