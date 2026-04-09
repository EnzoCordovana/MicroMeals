package fr.univamu.iut.micromealscommandes.commande;

import java.util.ArrayList;

public class Commande {

    protected int id;
    protected int abonneId;
    protected String dateCommande;
    protected String adresseLivraison;
    protected String dateLivraison;
    protected ArrayList<LigneCommande> lignes;
    protected double prixTotal;

    public Commande() {
    }

    public Commande(int abonneId, String adresseLivraison, String dateLivraison) {
        this.abonneId = abonneId;
        this.adresseLivraison = adresseLivraison;
        this.dateLivraison = dateLivraison;
        this.lignes = new ArrayList<>();
        this.prixTotal = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAbonneId() {
        return abonneId;
    }

    public void setAbonneId(int abonneId) {
        this.abonneId = abonneId;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(String dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public ArrayList<LigneCommande> getLignes() {
        return lignes;
    }

    public void setLignes(ArrayList<LigneCommande> lignes) {
        this.lignes = lignes;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", abonneId=" + abonneId + ", dateCommande='" + dateCommande + '\'' + ", adresseLivraison='" + adresseLivraison + '\'' + ", dateLivraison='" + dateLivraison + '\'' + ", lignes=" + lignes + ", prixTotal=" + prixTotal + '}';
    }
}
