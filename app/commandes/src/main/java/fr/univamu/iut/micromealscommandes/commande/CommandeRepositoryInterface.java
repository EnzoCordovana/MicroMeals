package fr.univamu.iut.micromealscommandes;

import java.util.ArrayList;

public interface CommandeRepositoryInterface {

    public void close();

    public Commande getCommande(int id);

    public ArrayList<Commande> getAllCommandes();

    public ArrayList<Commande> getCommandesByAbonneId(int abonneId);

    public int createCommande(Commande commande);

    public boolean updateCommande(int id, String adresseLivraison, String dateLivraison);

    public boolean deleteCommande(int id);
}
