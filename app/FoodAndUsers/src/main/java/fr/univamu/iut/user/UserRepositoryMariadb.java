package fr.univamu.iut.user;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'acceder aux utilisateurs stockes dans une base de donnees Mariadb.
 * Implemente UserRepositoryInterface.
 */
public class UserRepositoryMariadb implements UserRepositoryInterface, Closeable {

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
    public UserRepositoryMariadb(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
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
    public User getUser(int id) {
        User selectedUser = null;
        String query = "SELECT * FROM Utilisateur WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                selectedUser = new User(
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("adresse")
                );
                selectedUser.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUser;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> listUsers;
        String query = "SELECT * FROM Utilisateur";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            listUsers = new ArrayList<>();

            while (result.next()) {
                User currentUser = new User(
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("adresse")
                );
                currentUser.setId(result.getInt("id"));
                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUsers;
    }

    @Override
    public int createUser(String nom, String prenom, String email, String adresse) {
        String query = "INSERT INTO Utilisateur (nom, prenom, email, adresse) VALUES (?, ?, ?, ?)";
        int generatedId = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setString(4, adresse);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) generatedId = keys.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedId;
    }

    @Override
    public boolean deleteUser(int id) {
        String query = "DELETE FROM Utilisateur WHERE id=?";
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
    public boolean updateUser(int id, String nom, String prenom, String email, String adresse) {
        String query = "UPDATE Utilisateur SET nom=?, prenom=?, email=?, adresse=? WHERE id=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, email);
            ps.setString(4, adresse);
            ps.setInt(5, id);
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (nbRowModified != 0);
    }

    @Override
    public User getUserByEmail(String email) {
        User selectedUser = null;
        String query = "SELECT * FROM Utilisateur WHERE email=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                selectedUser = new User(
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("email"),
                        result.getString("adresse")
                );
                selectedUser.setId(result.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUser;
    }
}
