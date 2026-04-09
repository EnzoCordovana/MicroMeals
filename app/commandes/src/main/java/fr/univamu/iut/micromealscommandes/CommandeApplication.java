package fr.univamu.iut.micromealscommandes;

import fr.univamu.iut.micromealscommandes.commande.CommandeRepositoryInterface;
import fr.univamu.iut.micromealscommandes.commande.CommandeRepositoryMariadb;
import fr.univamu.iut.micromealscommandes.menu.MenuRepositoryAPI;
import fr.univamu.iut.micromealscommandes.menu.MenuRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Application JAX-RS principale configurant les connexions a la base de donnees
 * et a l'API externe des menus
 */
@ApplicationPath("/api")
@ApplicationScoped
public class CommandeApplication extends Application {

    /**
     * Cree et retourne la connexion a la base de donnees MariaDB des commandes
     *
     * @return une instance de {@link CommandeRepositoryInterface}
     */
    @Produces
    private CommandeRepositoryInterface openDbConnection() {
        CommandeRepositoryMariadb db = null;

        try {
            db = new CommandeRepositoryMariadb("jdbc:mariadb://mysql-archi-log-s4.alwaysdata.net/archi-log-s4_commandes_db", "archi-log-s4_commandes", "BDxJavaxGlassfish26");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Ferme la connexion à la base de données.
     *
     * @param commandeRepo le depot a fermer
     */
    private void closeDbConnection(@Disposes CommandeRepositoryInterface commandeRepo) {
        commandeRepo.close();
    }

    /**
     * Cree et retourne la connexion a l'API des menus
     *
     * @return une instance de {@link MenuRepositoryInterface}
     */
    @Produces
    private MenuRepositoryInterface openMenuApiConnection() {
        return new MenuRepositoryAPI("http://localhost:3004/");
    }

    /**
     * Ferme la connexion a l'API des menus
     *
     * @param menuRepo le depot a fermer
     */
    private void closeMenuApiConnection(@Disposes MenuRepositoryInterface menuRepo) {
        menuRepo.close();
    }
}
