package fr.univamu.iut.user;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests d'integration pour UserService
 * Necessite une base de donnees MySQL avec la table Utilisateur remplie
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    private static UserRepositoryMariadb userRepo;
    private static UserService userService;

    @BeforeAll
    static void setup() throws Exception {
        userRepo = new UserRepositoryMariadb(
                "jdbc:mysql://127.0.0.1:3307/db_plats?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "root"
        );
        userService = new UserService(userRepo);
    }

    @AfterAll
    static void teardown() {
        if (userRepo != null) {
            userRepo.close();
        }
    }

    @Test
    @Order(1)
    @DisplayName("getAllUsersJSON retourne un tableau JSON non vide")
    void testGetAllUsersJSON() {
        String json = userService.getAllUsersJSON();
        assertNotNull(json, "Le JSON ne devrait pas etre null");
        assertTrue(json.startsWith("["), "Le JSON devrait commencer par un crochet (tableau)");
        assertTrue(json.endsWith("]"), "Le JSON devrait finir par un crochet (tableau)");
    }

    @Test
    @Order(2)
    @DisplayName("getAllUsersJSON contient les champs attendus")
    void testGetAllUsersJSONChamps() {
        String json = userService.getAllUsersJSON();
        assertTrue(json.contains("\"nom\""), "Le JSON devrait contenir le champ nom");
        assertTrue(json.contains("\"prenom\""), "Le JSON devrait contenir le champ prenom");
        assertTrue(json.contains("\"email\""), "Le JSON devrait contenir le champ email");
        assertTrue(json.contains("\"adresse\""), "Le JSON devrait contenir le champ adresse");
        assertTrue(json.contains("\"id\""), "Le JSON devrait contenir le champ id");
    }

    @Test
    @Order(3)
    @DisplayName("getUserJSON retourne un objet JSON valide pour un id existant")
    void testGetUserJSON() {
        String json = userService.getUserJSON(1);
        assertNotNull(json, "Le JSON de l'utilisateur 1 ne devrait pas etre null");
        assertTrue(json.startsWith("{"), "Le JSON devrait commencer par une accolade (objet)");
        assertTrue(json.endsWith("}"), "Le JSON devrait finir par une accolade (objet)");
    }

    @Test
    @Order(4)
    @DisplayName("getUserJSON contient les champs attendus")
    void testGetUserJSONChamps() {
        String json = userService.getUserJSON(1);
        assertNotNull(json);
        assertTrue(json.contains("\"nom\""));
        assertTrue(json.contains("\"prenom\""));
        assertTrue(json.contains("\"email\""));
        assertTrue(json.contains("\"adresse\""));
        assertTrue(json.contains("\"id\""));
    }

    @Test
    @Order(5)
    @DisplayName("getUserJSON retourne null pour un id inexistant")
    void testGetUserJSONInexistant() {
        String json = userService.getUserJSON(-1);
        assertNull(json, "Le JSON d'un utilisateur inexistant devrait etre null");
    }

    @Test
    @Order(6)
    @DisplayName("updateUser met a jour un utilisateur existant et le JSON est modifie")
    void testUpdateUser() {
        User original = userRepo.getUser(1);
        assertNotNull(original);

        User updated = new User("Nom Service Test", "Prenom Service Test", "service.test@email.fr", "1 rue du Service");
        boolean result = userService.updateUser(1, updated);
        assertTrue(result, "La mise a jour devrait reussir");

        String modifiedJson = userService.getUserJSON(1);
        assertTrue(modifiedJson.contains("Nom Service Test"));
        assertTrue(modifiedJson.contains("service.test@email.fr"));

        // Restaurer
        userService.updateUser(1, original);
    }

    @Test
    @Order(7)
    @DisplayName("updateUser retourne false pour un id inexistant")
    void testUpdateUserInexistant() {
        User user = new User("Test", "Test", "test@test.fr", "Test");
        boolean result = userService.updateUser(-1, user);
        assertFalse(result, "La mise a jour d'un utilisateur inexistant devrait echouer");
    }
}
