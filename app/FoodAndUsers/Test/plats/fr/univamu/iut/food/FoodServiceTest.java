package fr.univamu.iut.food;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests d'integration pour FoodService
 * Necessite une base de donnees MariaDB/MySQL avec la table Food remplie
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FoodServiceTest {

    private static FoodRepositoryMariadb foodRepo;
    private static FoodService foodService;

    /**
     * Connexion a la base de donnees et creation du service avant tous les tests
     * TODO: Remplacer par vos informations de connexion
     */
    @BeforeAll
    static void setup() throws Exception {
        foodRepo = new FoodRepositoryMariadb(
                "jdbc:mysql://127.0.0.1:3307/db_plats?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "root"
        );
        foodService = new FoodService(foodRepo);
    }

    @AfterAll
    static void teardown() {
        if (foodRepo != null) {
            foodRepo.close();
        }
    }

    @Test
    @Order(1)
    @DisplayName("getAllFoodsJSON retourne un tableau JSON non vide")
    void testGetAllFoodsJSON() {
        String json = foodService.getAllFoodsJSON();
        assertNotNull(json, "Le JSON ne devrait pas etre null");
        assertTrue(json.startsWith("["), "Le JSON devrait commencer par un crochet (tableau)");
        assertTrue(json.endsWith("]"), "Le JSON devrait finir par un crochet (tableau)");
    }

    @Test
    @Order(2)
    @DisplayName("getAllFoodsJSON contient les champs attendus")
    void testGetAllFoodsJSONChamps() {
        String json = foodService.getAllFoodsJSON();
        assertTrue(json.contains("\"nom\""), "Le JSON devrait contenir le champ nom");
        assertTrue(json.contains("\"description\""), "Le JSON devrait contenir le champ description");
        assertTrue(json.contains("\"prix\""), "Le JSON devrait contenir le champ prix");
        assertTrue(json.contains("\"id\""), "Le JSON devrait contenir le champ id");
    }

    @Test
    @Order(3)
    @DisplayName("getFoodJSON retourne un objet JSON valide pour un id existant")
    void testGetFoodJSON() {
        String json = foodService.getFoodJSON(1);
        assertNotNull(json, "Le JSON du plat 1 ne devrait pas etre null");
        assertTrue(json.startsWith("{"), "Le JSON devrait commencer par une accolade (objet)");
        assertTrue(json.endsWith("}"), "Le JSON devrait finir par une accolade (objet)");
    }

    @Test
    @Order(4)
    @DisplayName("getFoodJSON contient les champs attendus")
    void testGetFoodJSONChamps() {
        String json = foodService.getFoodJSON(1);
        assertNotNull(json);
        assertTrue(json.contains("\"nom\""), "Le JSON devrait contenir le champ nom");
        assertTrue(json.contains("\"description\""), "Le JSON devrait contenir le champ description");
        assertTrue(json.contains("\"prix\""), "Le JSON devrait contenir le champ prix");
        assertTrue(json.contains("\"id\""), "Le JSON devrait contenir le champ id");
    }

    @Test
    @Order(5)
    @DisplayName("getFoodJSON retourne null pour un id inexistant")
    void testGetFoodJSONInexistant() {
        String json = foodService.getFoodJSON(-1);
        assertNull(json, "Le JSON d'un plat inexistant devrait etre null");
    }

    @Test
    @Order(6)
    @DisplayName("updateFood met a jour un plat existant et le JSON est modifie")
    void testUpdateFood() {
        // Sauvegarder l'original
        String originalJson = foodService.getFoodJSON(1);
        assertNotNull(originalJson);
        Food original = foodRepo.getFood(1);

        // Modifier via le service
        Food updated = new Food("Plat Service Test", "Description service test", 77.77);
        boolean result = foodService.updateFood(1, updated);
        assertTrue(result, "La mise a jour devrait reussir");

        // Verifier que le JSON retourne reflète la modification
        String modifiedJson = foodService.getFoodJSON(1);
        assertTrue(modifiedJson.contains("Plat Service Test"), "Le JSON devrait contenir le nouveau nom");
        assertTrue(modifiedJson.contains("Description service test"), "Le JSON devrait contenir la nouvelle description");
        assertTrue(modifiedJson.contains("77.77"), "Le JSON devrait contenir le nouveau prix");

        // Restaurer
        foodService.updateFood(1, original);
    }

    @Test
    @Order(7)
    @DisplayName("updateFood retourne false pour un id inexistant")
    void testUpdateFoodInexistant() {
        Food food = new Food("Test", "Test", 0);
        boolean result = foodService.updateFood(-1, food);
        assertFalse(result, "La mise a jour d'un plat inexistant devrait echouer");
    }
}
