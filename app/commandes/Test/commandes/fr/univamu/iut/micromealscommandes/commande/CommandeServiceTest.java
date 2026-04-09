package fr.univamu.iut.micromealscommandes.commande;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandeServiceTest {

    private static final String URL = "jdbc:mariadb://mysql-archi-log-s4.alwaysdata.net/archi-log-s4_commandes_db";
    private static final String USER = "archi-log-s4_commandes";
    private static final String PWD = "BDxJavaxGlassfish26";

    @Test
    void testGetAllCommandesJSON() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        CommandeService service = new CommandeService(repo, null);
        String json = service.getAllCommandesJSON();
        assertNotNull(json);
        assertTrue(json.startsWith("["));
        assertTrue(json.endsWith("]"));
        repo.close();
    }

    @Test
    void testGetCommandeJSONInexistant() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        CommandeService service = new CommandeService(repo, null);
        String json = service.getCommandeJSON(-1);
        assertNull(json);
        repo.close();
    }

    @Test
    void testGetCommandesByAbonneIdJSON() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        CommandeService service = new CommandeService(repo, null);
        String json = service.getCommandesByAbonneIdJSON(-1);
        assertNotNull(json);
        assertTrue(json.startsWith("["));
        assertTrue(json.endsWith("]"));
        repo.close();
    }

    @Test
    void testUpdateCommandeInexistant() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        CommandeService service = new CommandeService(repo, null);
        CommandeUpdateInput input = new CommandeUpdateInput("adresse", "2025-01-01");
        String result = service.updateCommande(-1, input);
        assertNull(result);
        repo.close();
    }

    @Test
    void testDeleteCommandeInexistant() throws Exception {
        CommandeRepositoryMariadb repo = new CommandeRepositoryMariadb(URL, USER, PWD);
        CommandeService service = new CommandeService(repo, null);
        boolean result = service.deleteCommande(-1);
        assertFalse(result);
        repo.close();
    }
}
