package fr.univamu.iut.micromealscommandes.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    void testConstructeurParDefaut() {
        Menu menu = new Menu();
        assertEquals(0, menu.getId());
        assertNull(menu.getNom());
        assertEquals(0.0, menu.getPrixTotal(), 0.001);
    }

    @Test
    void testConstructeurAvecParametres() {
        Menu menu = new Menu(1, "Menu du jour", 12.50);
        assertEquals(1, menu.getId());
        assertEquals("Menu du jour", menu.getNom());
        assertEquals(12.50, menu.getPrixTotal(), 0.001);
    }

    @Test
    void testSetters() {
        Menu menu = new Menu();
        menu.setId(5);
        menu.setNom("Menu Veggie");
        menu.setPrixTotal(9.90);

        assertEquals(5, menu.getId());
        assertEquals("Menu Veggie", menu.getNom());
        assertEquals(9.90, menu.getPrixTotal(), 0.001);
    }
}
