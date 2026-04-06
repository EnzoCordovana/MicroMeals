package fr.univamu.iut.user;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * Tests d'integration pour UserRepositoryMariadb
 * Necessite une base de donnees MySQL avec la table Utilisateur remplie
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryMariadbTest {

    private static UserRepositoryMariadb userRepo;

    @BeforeAll
    static void setup() throws Exception {
        userRepo = new UserRepositoryMariadb(
                "jdbc:mysql://127.0.0.1:3307/db_plats?allowPublicKeyRetrieval=true&useSSL=false",
                "root",
                "root"
        );
    }

    @AfterAll
    static void teardown() {
        if (userRepo != null) {
            userRepo.close();
        }
    }

    @Test
    @Order(1)
    @DisplayName("getAllUsers retourne une liste non vide")
    void testGetAllUsers() {
        ArrayList<User> users = userRepo.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty(), "La liste des utilisateurs ne devrait pas etre vide");
    }

    @Test
    @Order(2)
    @DisplayName("getAllUsers retourne des utilisateurs avec des informations completes")
    void testGetAllUsersContenu() {
        ArrayList<User> users = userRepo.getAllUsers();
        for (User user : users) {
            assertNotNull(user.getNom(), "Le nom ne devrait pas etre null");
            assertNotNull(user.getPrenom(), "Le prenom ne devrait pas etre null");
            assertNotNull(user.getEmail(), "L'email ne devrait pas etre null");
            assertNotNull(user.getAdresse(), "L'adresse ne devrait pas etre null");
            assertTrue(user.getId() > 0, "L'id devrait etre positif");
        }
    }

    @Test
    @Order(3)
    @DisplayName("getUser retourne le bon utilisateur pour un id existant")
    void testGetUserExistant() {
        User user = userRepo.getUser(1);
        assertNotNull(user, "L'utilisateur avec id=1 devrait exister");
        assertEquals(1, user.getId());
        assertNotNull(user.getNom());
        assertNotNull(user.getEmail());
    }

    @Test
    @Order(4)
    @DisplayName("getUser retourne null pour un id inexistant")
    void testGetUserInexistant() {
        User user = userRepo.getUser(-1);
        assertNull(user, "Un utilisateur inexistant devrait retourner null");
    }

    @Test
    @Order(5)
    @DisplayName("getUserByEmail retourne le bon utilisateur")
    void testGetUserByEmail() {
        // Recuperer le premier utilisateur pour connaitre son email
        ArrayList<User> users = userRepo.getAllUsers();
        assertFalse(users.isEmpty());
        String email = users.get(0).getEmail();

        User found = userRepo.getUserByEmail(email);
        assertNotNull(found, "L'utilisateur devrait etre trouve par email");
        assertEquals(email, found.getEmail());
    }

    @Test
    @Order(6)
    @DisplayName("getUserByEmail retourne null pour un email inexistant")
    void testGetUserByEmailInexistant() {
        User found = userRepo.getUserByEmail("email.inconnu@test.fr");
        assertNull(found, "Aucun utilisateur ne devrait etre trouve pour cet email");
    }

    @Test
    @Order(7)
    @DisplayName("updateUser modifie correctement un utilisateur existant")
    void testUpdateUser() {
        User original = userRepo.getUser(1);
        assertNotNull(original);

        boolean result = userRepo.updateUser(1, "NomTest", "PrenomTest", "test.update@email.fr", "1 rue de Test");
        assertTrue(result, "La mise a jour devrait reussir");

        User modified = userRepo.getUser(1);
        assertEquals("NomTest", modified.getNom());
        assertEquals("PrenomTest", modified.getPrenom());
        assertEquals("test.update@email.fr", modified.getEmail());

        // Restaurer
        userRepo.updateUser(1, original.getNom(), original.getPrenom(), original.getEmail(), original.getAdresse());
    }

    @Test
    @Order(8)
    @DisplayName("updateUser retourne false pour un id inexistant")
    void testUpdateUserInexistant() {
        boolean result = userRepo.updateUser(-1, "Test", "Test", "test@test.fr", "Test");
        assertFalse(result, "La mise a jour d'un utilisateur inexistant devrait echouer");
    }

    @Test
    @Order(9)
    @DisplayName("createUser et deleteUser fonctionnent correctement")
    void testCreateAndDeleteUser() {
        int newId = userRepo.createUser("Nouveau", "Utilisateur", "nouveau.utilisateur@test.fr", "99 rue Neuve");
        assertTrue(newId > 0, "L'id genere devrait etre positif");

        User created = userRepo.getUser(newId);
        assertNotNull(created);
        assertEquals("Nouveau", created.getNom());
        assertEquals("nouveau.utilisateur@test.fr", created.getEmail());

        boolean deleted = userRepo.deleteUser(newId);
        assertTrue(deleted, "La suppression devrait reussir");

        User afterDelete = userRepo.getUser(newId);
        assertNull(afterDelete, "L'utilisateur supprime ne devrait plus exister");
    }

    @Test
    @Order(10)
    @DisplayName("deleteUser retourne false pour un id inexistant")
    void testDeleteUserInexistant() {
        boolean result = userRepo.deleteUser(-1);
        assertFalse(result, "La suppression d'un utilisateur inexistant devrait echouer");
    }
}
