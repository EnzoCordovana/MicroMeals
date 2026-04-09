package fr.univamu.iut.micromealscommandes.commande;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Implementation de {@link CommandeRepositoryInterface} utilisant une base de donnees MariaDB
 */
public class CommandeRepositoryMariadb implements CommandeRepositoryInterface, Closeable {

    protected Connection dbConnection;

    /**
     * Initialise la connexion a la base de donnees
     *
     * @param infoConnection URL de connexion JDBC
     * @param user           nom d'utilisateur
     * @param pwd            mot de passe
     * @throws SQLException           en cas d'erreur SQL
     * @throws ClassNotFoundException si le driver MariaDB est introuvable
     */
    public CommandeRepositoryMariadb(String infoConnection, String user, String pwd) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
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
    public Commande getCommande(int id) {
        Commande selectedCommande = null;

        String query = "SELECT * FROM Commande WHERE id=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);

            ResultSet result = ps.executeQuery();

            if (result.next()) {
                int abonneId = result.getInt("abonneId");
                String dateCommande = result.getString("dateCommande");
                String adresseLivraison = result.getString("adresseLivraison");
                String dateLivraison = result.getString("dateLivraison");
                double prixTotal = result.getDouble("prixTotal");

                selectedCommande = new Commande(abonneId, adresseLivraison, dateLivraison);
                selectedCommande.setId(id);
                selectedCommande.setDateCommande(dateCommande);
                selectedCommande.setPrixTotal(prixTotal);
                selectedCommande.setLignes(getLignesCommande(id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedCommande;
    }

    @Override
    public ArrayList<Commande> getAllCommandes() {
        ArrayList<Commande> listCommandes;

        String query = "SELECT * FROM Commande";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();

            listCommandes = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id");
                int abonneId = result.getInt("abonneId");
                String dateCommande = result.getString("dateCommande");
                String adresseLivraison = result.getString("adresseLivraison");
                String dateLivraison = result.getString("dateLivraison");
                double prixTotal = result.getDouble("prixTotal");

                Commande currentCommande = new Commande(abonneId, adresseLivraison, dateLivraison);
                currentCommande.setId(id);
                currentCommande.setDateCommande(dateCommande);
                currentCommande.setPrixTotal(prixTotal);
                currentCommande.setLignes(getLignesCommande(id));

                listCommandes.add(currentCommande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCommandes;
    }

    @Override
    public ArrayList<Commande> getCommandesByAbonneId(int abonneId) {
        ArrayList<Commande> listCommandes;

        String query = "SELECT * FROM Commande WHERE abonneId=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, abonneId);
            ResultSet result = ps.executeQuery();

            listCommandes = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id");
                String dateCommande = result.getString("dateCommande");
                String adresseLivraison = result.getString("adresseLivraison");
                String dateLivraison = result.getString("dateLivraison");
                double prixTotal = result.getDouble("prixTotal");

                Commande currentCommande = new Commande(abonneId, adresseLivraison, dateLivraison);
                currentCommande.setId(id);
                currentCommande.setDateCommande(dateCommande);
                currentCommande.setPrixTotal(prixTotal);
                currentCommande.setLignes(getLignesCommande(id));

                listCommandes.add(currentCommande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCommandes;
    }

    @Override
    public int createCommande(Commande commande) {
        int generatedId = -1;

        String queryCommande = "INSERT INTO Commande (abonneId, dateCommande, adresseLivraison, dateLivraison, prixTotal) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = dbConnection.prepareStatement(queryCommande, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, commande.getAbonneId());
            ps.setString(2, commande.getDateCommande());
            ps.setString(3, commande.getAdresseLivraison());
            ps.setString(4, commande.getDateLivraison());
            ps.setDouble(5, commande.getPrixTotal());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

            if (generatedId != -1 && commande.getLignes() != null) {
                String queryLigne = "INSERT INTO LigneCommande (commandeId, menuId, menuNom, quantite, prixUnitaire, prixLigne) VALUES (?, ?, ?, ?, ?, ?)";

                for (LigneCommande ligne : commande.getLignes()) {
                    try (PreparedStatement psLigne = dbConnection.prepareStatement(queryLigne)) {
                        psLigne.setInt(1, generatedId);
                        psLigne.setInt(2, ligne.getMenuId());
                        psLigne.setString(3, ligne.getMenuNom());
                        psLigne.setInt(4, ligne.getQuantite());
                        psLigne.setDouble(5, ligne.getPrixUnitaire());
                        psLigne.setDouble(6, ligne.getPrixLigne());

                        psLigne.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return generatedId;
    }

    @Override
    public boolean updateCommande(int id, String adresseLivraison, String dateLivraison) {
        String query = "UPDATE Commande SET adresseLivraison=?, dateLivraison=? WHERE id=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, adresseLivraison);
            ps.setString(2, dateLivraison);
            ps.setInt(3, id);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }

    @Override
    public boolean deleteCommande(int id) {
        String query = "DELETE FROM Commande WHERE id=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }

    /**
     * Recupere les lignes de commande associees a une commande.
     *
     * @param commandeId identifiant de la commande
     * @return liste des lignes de commande
     */
    private ArrayList<LigneCommande> getLignesCommande(int commandeId) {
        ArrayList<LigneCommande> lignes = new ArrayList<>();

        String query = "SELECT * FROM LigneCommande WHERE commandeId=?";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, commandeId);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int menuId = result.getInt("menuId");
                String menuNom = result.getString("menuNom");
                int quantite = result.getInt("quantite");
                double prixUnitaire = result.getDouble("prixUnitaire");

                LigneCommande ligne = new LigneCommande(menuId, menuNom, quantite, prixUnitaire);
                lignes.add(ligne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lignes;
    }
}
