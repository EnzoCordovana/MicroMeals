package fr.univamu.iut.food;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class FoodAndUserApplication extends Application {

    /**
     * Methode appelee par l'API CDI pour injecter la connexion a la base de donnees au moment de la creation
     * de la ressource
     * @return un objet implementant l'interface FoodRepositoryInterface utilisee
     *          pour acceder aux donnees des plats, voire les modifier
     */
    @Produces
    private FoodRepositoryInterface openDbConnection() {
        FoodRepositoryMariadb db = null;

        try {
            db = new FoodRepositoryMariadb("jdbc:mariadb://db_plats:3306/db_plats", "plats_user", "plats_pass");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Methode permettant de fermer la connexion a la base de donnees lorsque l'application est arretee
     * @param foodRepo la connexion a la base de donnees instanciee dans la methode openDbConnection
     */
    private void closeDbConnection(@Disposes FoodRepositoryInterface foodRepo) {
        foodRepo.close();
    }
}
