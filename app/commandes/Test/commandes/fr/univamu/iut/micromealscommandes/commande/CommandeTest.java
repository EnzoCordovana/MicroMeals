package fr.univamu.iut.micromealscommandes.commande;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandeTest {

    @Test
    void testConstructeurParDefaut() {
        Commande commande = new Commande();
        assertEquals(0, commande.getId());
        assertNull(commande.getAdresseLivraison());
        assertNull(commande.getDateLivraison());
    }

    @Test
    void testConstructeurAvecParametres() {
        Commande commande = new Commande(1, "12 rue de la Paix", "2025-06-01");
        assertEquals(1, commande.getAbonneId());
        assertEquals("12 rue de la Paix", commande.getAdresseLivraison());
        assertEquals("2025-06-01", commande.getDateLivraison());
        assertNotNull(commande.getLignes());
    }

    @Test
    void testSetters() {
        Commande commande = new Commande();
        commande.setId(10);
        commande.setAdresseLivraison("5 avenue du Port");
        commande.setPrixTotal(29.90);

        assertEquals(10, commande.getId());
        assertEquals("5 avenue du Port", commande.getAdresseLivraison());
        assertEquals(29.90, commande.getPrixTotal(), 0.001);
    }
}
