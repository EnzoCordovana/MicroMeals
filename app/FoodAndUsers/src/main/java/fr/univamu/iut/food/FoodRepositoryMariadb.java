package fr.univamu.iut.food;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'acceder aux plats stockes dans une base de donnees Mariadb
 */
public class FoodRepositoryMariadb implements FoodRepositoryInterface, Closeable {

    /**
     * Acces a la base de donnees (session)
     */
    protected Connection dbConnection;

    /**
     * Constructeur de la classe
     * @param infoConnection chaine de caracteres avec les informations de connexion
     * @param user chaine de caracteres contenant l'identifiant de connexion a la base de donnees
     * @param pwd chaine de caracteres contenant le mot de passe a utiliser
     */
    public FoodRepositoryMariadb(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Food getFood(int id) {

        Food selectedFood = null;

        String query = "SELECT * FROM Food WHERE id=?";

        // construction et execution d'une requete preparee
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);

            // execution de la requete
            ResultSet result = ps.executeQuery();

            // recuperation du premier (et seul) tuple resultat
            // (si l'identifiant du plat est valide)
            if (result.next()) {
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");

                // creation et initialisation de l'objet Food
                selectedFood = new Food(nom, description, prix);
                selectedFood.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedFood;
    }

    @Override
    public ArrayList<Food> getAllFoods() {
        ArrayList<Food> listFoods;

        String query = "SELECT * FROM Food";

        // construction et execution d'une requete preparee
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            // execution de la requete
            ResultSet result = ps.executeQuery();

            listFoods = new ArrayList<>();

            // parcours du resultat
            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String description = result.getString("description");
                double prix = result.getDouble("prix");

                // creation du plat courant
                Food currentFood = new Food(nom, description, prix);
                currentFood.setId(id);

                listFoods.add(currentFood);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listFoods;
    }

    @Override
    public int createFood(String nom, String description, double prix) {
        String query = "INSERT INTO Food (nom, description, prix) VALUES (?, ?, ?)";
        int generatedId = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nom);
            ps.setString(2, description);
            ps.setDouble(3, prix);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) generatedId = keys.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedId;
    }

    @Override
    public boolean deleteFood(int id) {
        String query = "DELETE FROM Food WHERE id=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    @Override
    public boolean updateFood(int id, String nom, String description, double prix) {
        String query = "UPDATE Food SET nom=?, description=?, prix=? WHERE id=?";
        int nbRowModified = 0;

        // construction et execution d'une requete preparee
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setString(2, description);
            ps.setDouble(3, prix);
            ps.setInt(4, id);

            // execution de la requete
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }
}
