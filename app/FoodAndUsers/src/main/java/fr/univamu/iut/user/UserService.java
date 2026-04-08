package fr.univamu.iut.user;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisee pour recuperer les informations necessaires a la ressource
 * (permet de dissocier ressource et mode d'acces aux donnees)
 */
public class UserService {

    /**
     * Objet permettant d'acceder au depot ou sont stockees les informations sur les utilisateurs
     */
    protected UserRepositoryInterface userRepo;

    /**
     * Constructeur permettant d'injecter l'acces aux donnees
     * @param userRepo objet implementant l'interface d'acces aux donnees
     */
    public UserService(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Methode retournant les informations sur tous les utilisateurs au format JSON
     * @return une chaine de caractere contenant les informations au format JSON
     */
    public String getAllUsersJSON() {
        ArrayList<User> allUsers = userRepo.getAllUsers();

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUsers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Methode retournant au format JSON les informations sur un utilisateur recherche
     * @param id l'identifiant de l'utilisateur recherche
     * @return une chaine de caractere contenant les informations au format JSON
     */
    public String getUserJSON(int id) {
        String result = null;
        User myUser = userRepo.getUser(id);

        if (myUser != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Methode permettant de mettre a jour les informations d'un utilisateur
     * @param id identifiant de l'utilisateur a mettre a jour
     * @param user les nouvelles informations a utiliser
     * @return true si l'utilisateur a pu etre mis a jour
     */
    public boolean updateUser(int id, User user) {
        return userRepo.updateUser(id, user.nom, user.prenom, user.email, user.adresse);
    }

    /**
     * Methode permettant de creer un nouvel utilisateur et de retourner ses informations au format JSON
     * @param user les informations de l'utilisateur a creer
     * @return une chaine de caractere contenant les informations de l'utilisateur cree au format JSON
     */
    public String createUserJSON(User user) {
        int newId = userRepo.createUser(user.getNom(), user.getPrenom(), user.getEmail(), user.getAdresse(), "");
        user.setId(newId);

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(user);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Methode permettant de supprimer un utilisateur
     * @param id identifiant de l'utilisateur a supprimer
     * @return true si l'utilisateur a ete supprime, false sinon
     */
    public boolean deleteUser(int id) {
        return userRepo.deleteUser(id);
    }
}
