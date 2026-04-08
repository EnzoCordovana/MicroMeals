package fr.univamu.iut.user;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe User (pas de base de donnees necessaire)
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    @Test
    @Order(1)
    @DisplayName("Constructeur par defaut cree un User vide")
    void testConstructeurParDefaut() {
        User user = new User();
        assertNull(user.getNom());
        assertNull(user.getPrenom());
        assertNull(user.getEmail());
        assertNull(user.getAdresse());
        assertEquals(0, user.getId());
    }

    @Test
    @Order(2)
    @DisplayName("Constructeur avec parametres initialise correctement")
    void testConstructeurAvecParametres() {
        User user = new User("Dupont", "Marie", "marie.dupont@email.fr", "12 rue des Lilas");
        assertEquals("Dupont", user.getNom());
        assertEquals("Marie", user.getPrenom());
        assertEquals("marie.dupont@email.fr", user.getEmail());
        assertEquals("12 rue des Lilas", user.getAdresse());
    }

    @Test
    @Order(3)
    @DisplayName("Setters modifient les champs correctement")
    void testSetters() {
        User user = new User();
        user.setId(42);
        user.setNom("Martin");
        user.setPrenom("Jean");
        user.setEmail("jean.martin@email.fr");
        user.setAdresse("5 avenue du Port");

        assertEquals(42, user.getId());
        assertEquals("Martin", user.getNom());
        assertEquals("Jean", user.getPrenom());
        assertEquals("jean.martin@email.fr", user.getEmail());
        assertEquals("5 avenue du Port", user.getAdresse());
    }

    @Test
    @Order(4)
    @DisplayName("toString contient les informations du User")
    void testToString() {
        User user = new User("Petit", "Sophie", "sophie.petit@email.fr", "8 bd Longchamp");
        user.setId(3);
        String s = user.toString();
        assertTrue(s.contains("Petit"));
        assertTrue(s.contains("Sophie"));
        assertTrue(s.contains("sophie.petit@email.fr"));
    }

    @Test
    @Order(5)
    @DisplayName("Deux Users avec les memes donnees ont les memes valeurs")
    void testEqualiteManuelle() {
        User u1 = new User("Durand", "Claire", "claire.durand@email.fr", "3 cours Julien");
        User u2 = new User("Durand", "Claire", "claire.durand@email.fr", "3 cours Julien");
        assertEquals(u1.getNom(), u2.getNom());
        assertEquals(u1.getEmail(), u2.getEmail());
    }

    @Test
    @Order(6)
    @DisplayName("Constructeur avec password initialise correctement le mot de passe")
    void testConstructeurAvecPassword() {
        User user = new User("Dupont", "Marie", "marie.dupont@email.fr", "12 rue des Lilas", "secret");
        assertEquals("secret", user.getPassword());
    }

    @Test
    @Order(7)
    @DisplayName("setPassword modifie le mot de passe correctement")
    void testSetPassword() {
        User user = new User();
        assertNull(user.getPassword());
        user.setPassword("monMotDePasse");
        assertEquals("monMotDePasse", user.getPassword());
    }
}
