package fr.univamu.iut.food;

import java.util.ArrayList;

/**
 * Interface d'acces aux donnees des plats
 */
public interface FoodRepositoryInterface {

    /**
     * Methode fermant le depot ou sont stockees les informations sur les plats
     */
    public void close();

    /**
     * Methode retournant le plat dont l'identifiant est passe en parametre
     * @param id identifiant du plat recherche
     * @return un objet Food representant le plat recherche
     */
    public Food getFood(int id);

    /**
     * Methode retournant la liste des plats
     * @return une liste d'objets Food
     */
    public ArrayList<Food> getAllFoods();

    /**
     * Methode permettant de mettre a jour un plat enregistre
     * @param id identifiant du plat a mettre a jour
     * @param nom nouveau nom du plat
     * @param description nouvelle description du plat
     * @param prix nouveau prix du plat
     * @return true si le plat existe et la mise a jour a ete faite, false sinon
     */
    public boolean updateFood(int id, String nom, String description, double prix);

    /**
     * Methode permettant de creer un nouveau plat
     * @param nom nom du plat
     * @param description description du plat
     * @param prix prix du plat
     * @return l'identifiant genere pour le nouveau plat
     */
    public int createFood(String nom, String description, double prix);

    /**
     * Methode permettant de supprimer un plat
     * @param id identifiant du plat a supprimer
     * @return true si le plat a ete supprime, false s'il n'existait pas
     */
    public boolean deleteFood(int id);
}
