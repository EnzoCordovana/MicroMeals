package fr.univamu.iut.food;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour l'entite Food (aucune connexion BD requise)
 */
public class FoodTest {

    @Test
    @DisplayName("Constructeur par defaut cree un objet Food vide")
    void testConstructeurParDefaut() {
        Food food = new Food();
        assertEquals(0, food.getId());
        assertNull(food.getNom());
        assertNull(food.getDescription());
        assertEquals(0.0, food.getPrix(), 0.01);
    }

    @Test
    @DisplayName("Constructeur avec parametres initialise correctement les attributs")
    void testConstructeurAvecParametres() {
        Food food = new Food("Salade nicoise", "Salade composee avec thon et olives", 8.50);
        assertEquals("Salade nicoise", food.getNom());
        assertEquals("Salade composee avec thon et olives", food.getDescription());
        assertEquals(8.50, food.getPrix(), 0.01);
    }

    @Test
    @DisplayName("Les setters modifient correctement les attributs")
    void testSetters() {
        Food food = new Food();

        food.setId(5);
        food.setNom("Gratin dauphinois");
        food.setDescription("Gratin de pommes de terre");
        food.setPrix(9.00);

        assertEquals(5, food.getId());
        assertEquals("Gratin dauphinois", food.getNom());
        assertEquals("Gratin de pommes de terre", food.getDescription());
        assertEquals(9.00, food.getPrix(), 0.01);
    }

    @Test
    @DisplayName("toString retourne une representation lisible")
    void testToString() {
        Food food = new Food("Bouillabaisse", "Soupe de poissons", 15.50);
        food.setId(4);

        String result = food.toString();
        assertTrue(result.contains("Bouillabaisse"));
        assertTrue(result.contains("Soupe de poissons"));
        assertTrue(result.contains("15.5"));
        assertTrue(result.contains("4"));
    }

    @Test
    @DisplayName("Le prix peut etre modifie via le setter")
    void testModificationPrix() {
        Food food = new Food("Tian de legumes", "Gratin de courgettes", 7.50);
        food.setPrix(8.00);
        assertEquals(8.00, food.getPrix(), 0.01);
    }
}
