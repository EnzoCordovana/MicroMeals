package fr.univamu.iut.micromealscommandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class CommandeApplication extends Application {

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

    private void closeDbConnection(@Disposes CommandeRepositoryInterface commandeRepo) {
        commandeRepo.close();
    }

    @Produces
    private MenuRepositoryInterface openMenuApiConnection() {
        return new MenuRepositoryAPI("http://localhost:3004/");
    }

    private void closeMenuApiConnection(@Disposes MenuRepositoryInterface menuRepo) {
        menuRepo.close();
    }
}
