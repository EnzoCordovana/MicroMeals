package fr.univamu.iut.food;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * Tests d'integration pour FoodRepositoryMariadb
 * Necessite une base de donnees MariaDB/MySQL avec la table Food remplie
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FoodRepositoryMariadbTest {

    private static FoodRepositoryMariadb foodRepo;

    /**
     * Connexion a la base de donnees avant tous les tests
     * TODO: Remplacer par vos informations de connexion
     */
    @BeforeAll
    static void setup() throws Exception {
        foodRepo = new FoodRepositoryMariadb(
                "jdbc:mysql://127.0.0.1:3307/db_plats?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "root"
        );
    }

    @AfterAll
    static void teardown() {
        if (foodRepo != null) {
            foodRepo.close();
        }
    }

    @Test
    @Order(1)
    @DisplayName("getAllFoods retourne une liste non vide")
    void testGetAllFoods() {
        ArrayList<Food> foods = foodRepo.getAllFoods();
        assertNotNull(foods);
        assertFalse(foods.isEmpty(), "La liste des plats ne devrait pas etre vide");
    }

    @Test
    @Order(2)
    @DisplayName("getAllFoods retourne des plats avec des informations completes")
    void testGetAllFoodsContenu() {
        ArrayList<Food> foods = foodRepo.getAllFoods();
        for (Food food : foods) {
            assertNotNull(food.getNom(), "Le nom ne devrait pas etre null");
            assertNotNull(food.getDescription(), "La description ne devrait pas etre null");
            assertTrue(food.getPrix() > 0, "Le prix devrait etre positif");
            assertTrue(food.getId() > 0, "L'id devrait etre positif");
        }
    }

    @Test
    @Order(3)
    @DisplayName("getFood retourne le bon plat pour un id existant")
    void testGetFoodExistant() {
        Food food = foodRepo.getFood(1);
        assertNotNull(food, "Le plat avec id=1 devrait exister");
        assertEquals(1, food.getId());
        assertNotNull(food.getNom());
        assertNotNull(food.getDescription());
        assertTrue(food.getPrix() > 0, "Le prix devrait etre positif");
    }

    @Test
    @Order(4)
    @DisplayName("getFood retourne null pour un id inexistant")
    void testGetFoodInexistant() {
        Food food = foodRepo.getFood(-1);
        assertNull(food, "Un plat inexistant devrait retourner null");
    }

    @Test
    @Order(5)
    @DisplayName("updateFood modifie correctement un plat existant")
    void testUpdateFood() {
        // Recuperer le plat original pour le restaurer apres
        Food original = foodRepo.getFood(1);
        assertNotNull(original);

        // Modifier le plat
        boolean result = foodRepo.updateFood(1, "Plat Test Modifie", "Description de test", 99.99);
        assertTrue(result, "La mise a jour devrait reussir");

        // Verifier que la modification a ete appliquee
        Food modified = foodRepo.getFood(1);
        assertEquals("Plat Test Modifie", modified.getNom());
        assertEquals("Description de test", modified.getDescription());
        assertEquals(99.99, modified.getPrix(), 0.01);

        // Restaurer les valeurs originales
        foodRepo.updateFood(1, original.getNom(), original.getDescription(), original.getPrix());
    }

    @Test
    @Order(6)
    @DisplayName("updateFood retourne false pour un id inexistant")
    void testUpdateFoodInexistant() {
        boolean result = foodRepo.updateFood(-1, "Test", "Test", 0);
        assertFalse(result, "La mise a jour d'un plat inexistant devrait echouer");
    }

    @Test
    @Order(7)
    @DisplayName("getFood retourne les memes donnees que celles dans getAllFoods")
    void testCoherenceGetFoodEtGetAllFoods() {
        ArrayList<Food> allFoods = foodRepo.getAllFoods();
        assertFalse(allFoods.isEmpty());

        Food first = allFoods.get(0);
        Food fetched = foodRepo.getFood(first.getId());

        assertNotNull(fetched);
        assertEquals(first.getId(), fetched.getId());
        assertEquals(first.getNom(), fetched.getNom());
        assertEquals(first.getDescription(), fetched.getDescription());
        assertEquals(first.getPrix(), fetched.getPrix(), 0.01);
    }
}
