package fr.univamu.iut.food;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisee pour recuperer les informations necessaires a la ressource
 * (permet de dissocier ressource et mode d'acces aux donnees)
 */
public class FoodService {

    /**
     * Objet permettant d'acceder au depot ou sont stockees les informations sur les plats
     */
    protected FoodRepositoryInterface foodRepo;

    /**
     * Constructeur permettant d'injecter l'acces aux donnees
     * @param foodRepo objet implementant l'interface d'acces aux donnees
     */
    public FoodService(FoodRepositoryInterface foodRepo) {
        this.foodRepo = foodRepo;
    }

    /**
     * Methode retournant les informations sur les plats au format JSON
     * @return une chaine de caractere contenant les informations au format JSON
     */
    public String getAllFoodsJSON() {

        ArrayList<Food> allFoods = foodRepo.getAllFoods();

        // creation du json et conversion de la liste de plats
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allFoods);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    /**
     * Methode retournant au format JSON les informations sur un plat recherche
     * @param id l'identifiant du plat recherche
     * @return une chaine de caractere contenant les informations au format JSON
     */
    public String getFoodJSON(int id) {
        String result = null;
        Food myFood = foodRepo.getFood(id);

        // si le plat a ete trouve
        if (myFood != null) {

            // creation du json et conversion du plat
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myFood);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Methode permettant de mettre a jour les informations d'un plat
     * @param id identifiant du plat a mettre a jour
     * @param food les nouvelles informations a utiliser
     * @return true si le plat a pu etre mis a jour
     */
    public boolean updateFood(int id, Food food) {
        return foodRepo.updateFood(id, food.nom, food.description, food.prix);
    }

    /**
     * Methode permettant de creer un nouveau plat et de retourner ses informations au format JSON
     * @param food les informations du plat a creer
     * @return une chaine de caractere contenant les informations du plat cree au format JSON
     */
    public String createFoodJSON(Food food) {
        int newId = foodRepo.createFood(food.getNom(), food.getDescription(), food.getPrix());
        food.setId(newId);

        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(food);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    /**
     * Methode permettant de supprimer un plat
     * @param id identifiant du plat a supprimer
     * @return true si le plat a ete supprime, false sinon
     */
    public boolean deleteFood(int id) {
        return foodRepo.deleteFood(id);
    }
}
