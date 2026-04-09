package fr.univamu.iut.micromealscommandes.commande;

import java.util.ArrayList;

/**
 * Objet d'entree contenant les donnees necessaires a la creation d'une commande
 */
public class CommandeInput {

    protected int abonneId;
    protected String adresseLivraison;
    protected String dateLivraison;
    protected ArrayList<LigneCommandeInput> lignes;

    public CommandeInput() {
    }

    /**
     * Cree un objet d'entree avec toutes les informations de commande
     *
     * @param abonneId         identifiant de l'abonne
     * @param adresseLivraison adresse de livraison
     * @param dateLivraison    date souhaitee de livraison
     * @param lignes           lignes de la commande
     */
    public CommandeInput(int abonneId, String adresseLivraison, String dateLivraison, ArrayList<LigneCommandeInput> lignes) {
        this.abonneId = abonneId;
        this.adresseLivraison = adresseLivraison;
        this.dateLivraison = dateLivraison;
        this.lignes = lignes;
    }

    public int getAbonneId() {
        return abonneId;
    }

    public void setAbonneId(int abonneId) {
        this.abonneId = abonneId;
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

    public ArrayList<LigneCommandeInput> getLignes() {
        return lignes;
    }

    public void setLignes(ArrayList<LigneCommandeInput> lignes) {
        this.lignes = lignes;
    }
}
