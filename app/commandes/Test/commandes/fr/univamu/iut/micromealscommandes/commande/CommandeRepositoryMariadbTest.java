package fr.univamu.iut.micromealscommandes.commande;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class CommandeRepositoryMariadbTest {

    private static final String URL = "jdbc:mariadb://mysql-archi-log-s4.alwaysdata.net/archi-log-s4_commandes_db";
    private static final String USER = "archi-log-s4_commandes";
    private static final String PWD = "BDxJavaxGlassfish26";

    @Test
    void testGetAllCommandes() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        ArrayList<Commande> commandes = repo.getAllCommandes();
        assertNotNull(commandes);
        repo.close();
    }

    @Test
    void testGetCommandeInexistant() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        Commande commande = repo.getCommande(-1);
        assertNull(commande);
        repo.close();
    }

    @Test
    void testGetCommandesByAbonneIdInexistant() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        ArrayList<Commande> commandes = repo.getCommandesByAbonneId(-1);
        assertNotNull(commandes);
        assertTrue(commandes.isEmpty());
        repo.close();
    }

    @Test
    void testCreateAndDelete() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        Commande commande = new Commande(1, "1 rue du Test", "2025-12-01");
        commande.setDateCommande("2025-11-01T10:00:00");
        commande.setPrixTotal(0.0);

        int id = repo.createCommande(commande);
        assertTrue(id > 0);

        boolean deleted = repo.deleteCommande(id);
        assertTrue(deleted);
        repo.close();
    }

    @Test
    void testUpdateCommandeInexistant() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        boolean result = repo.updateCommande(-1, "adresse", "2025-01-01");
        assertFalse(result);
        repo.close();
    }

    @Test
    void testDeleteCommandeInexistant() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        boolean result = repo.deleteCommande(-1);
        assertFalse(result);
        repo.close();
    }
}
