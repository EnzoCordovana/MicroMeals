package fr.univamu.iut.micromealscommandes.commande;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LigneCommandeTest {

    @Test
    void testConstructeurParDefaut() {
        LigneCommande ligne = new LigneCommande();
        assertEquals(0, ligne.getMenuId());
        assertNull(ligne.getMenuNom());
        assertEquals(0.0, ligne.getPrixLigne(), 0.001);
    }

    @Test
    void testConstructeurCalculPrixLigne() {
        LigneCommande ligne = new LigneCommande(1, "Menu Test", 2, 10.0);
        assertEquals("Menu Test", ligne.getMenuNom());
        assertEquals(2, ligne.getQuantite());
        assertEquals(20.0, ligne.getPrixLigne(), 0.001);
    }

    @Test
    void testSetters() {
        LigneCommande ligne = new LigneCommande();
        ligne.setMenuNom("Menu Gourmet");
        ligne.setQuantite(3);
        ligne.setPrixUnitaire(8.0);

        assertEquals("Menu Gourmet", ligne.getMenuNom());
        assertEquals(3, ligne.getQuantite());
        assertEquals(8.0, ligne.getPrixUnitaire(), 0.001);
    }
}
