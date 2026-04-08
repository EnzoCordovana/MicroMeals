package fr.univamu.iut.user;

import java.util.ArrayList;

/**
 * Interface d'acces aux donnees des utilisateurs
 */
public interface UserRepositoryInterface {

    /**
     * Methode fermant le depot ou sont stockees les informations sur les utilisateurs
     */
    public void close();

    /**
     * Methode retournant l'utilisateur dont l'identifiant est passe en parametre
     * @param id identifiant de l'utilisateur recherche
     * @return un objet User representant l'utilisateur recherche
     */
    public User getUser(int id);

    /**
     * Methode retournant la liste de tous les utilisateurs
     * @return une liste d'objets User
     */
    public ArrayList<User> getAllUsers();

    /**
     * Methode permettant de mettre a jour un utilisateur enregistre
     * @param id identifiant de l'utilisateur a mettre a jour
     * @param nom nouveau nom
     * @param prenom nouveau prenom
     * @param email nouvel email
     * @param adresse nouvelle adresse
     * @return true si l'utilisateur existe et la mise a jour a ete faite, false sinon
     */
    public boolean updateUser(int id, String nom, String prenom, String email, String adresse);

    /**
     * Methode permettant de creer un nouvel utilisateur
     * @param nom nom de l'utilisateur
     * @param prenom prenom de l'utilisateur
     * @param email adresse email de l'utilisateur
     * @param adresse adresse postale de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return l'identifiant genere pour le nouvel utilisateur
     */
    public int createUser(String nom, String prenom, String email, String adresse, String password);

    /**
     * Methode permettant de supprimer un utilisateur
     * @param id identifiant de l'utilisateur a supprimer
     * @return true si l'utilisateur a ete supprime, false s'il n'existait pas
     */
    public boolean deleteUser(int id);

    /**
     * Methode retournant un utilisateur par son adresse email
     * @param email l'adresse email recherchee
     * @return un objet User si l'email existe, null sinon
     */
    public User getUserByEmail(String email);
}
