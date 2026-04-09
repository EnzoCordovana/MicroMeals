package fr.univamu.iut;

import fr.univamu.iut.food.FoodRepositoryInterface;
import fr.univamu.iut.food.FoodRepositoryMariadb;
import fr.univamu.iut.user.UserRepositoryInterface;
import fr.univamu.iut.user.UserRepositoryMariadb;

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
     * de FoodResource.
     * @return un objet implementant FoodRepositoryInterface pour acceder aux plats
     */
    @Produces
    private FoodRepositoryInterface openFoodDbConnection() {
        FoodRepositoryMariadb db = null;

        try {
            db = new FoodRepositoryMariadb("jdbc:mariadb://db_plats:3306/db_plats", "plats_user", "plats_pass");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Methode permettant de fermer la connexion Food lorsque l'application est arretee
     * @param foodRepo la connexion instanciee dans openFoodDbConnection
     */
    private void closeFoodDbConnection(@Disposes FoodRepositoryInterface foodRepo) {
        foodRepo.close();
    }

    /**
     * Methode appelee par l'API CDI pour injecter la connexion a la base de donnees au moment de la creation
     * de UserResource et UserAuthentificationRessource.
     * @return un objet implementant UserRepositoryInterface pour acceder aux utilisateurs
     */
    @Produces
    private UserRepositoryInterface openUserDbConnection() {
        UserRepositoryMariadb db = null;

        try {
            db = new UserRepositoryMariadb("jdbc:mariadb://db_plats:3306/db_plats", "plats_user", "plats_pass");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Methode permettant de fermer la connexion User lorsque l'application est arretee
     * @param userRepo la connexion instanciee dans openUserDbConnection
     */
    private void closeUserDbConnection(@Disposes UserRepositoryInterface userRepo) {
        userRepo.close();
    }
}
