package fr.univamu.iut.micromealscommandes.commande;

/**
 * Objet d'entree pour la mise a jour des informations de livraison d'une commande
 */
public class CommandeUpdateInput {

    protected String adresseLivraison;
    protected String dateLivraison;

    public CommandeUpdateInput() {
    }

    /**
     * Cree un objet de mise a jour avec les nouvelles informations de livraison
     *
     * @param adresseLivraison nouvelle adresse de livraison
     * @param dateLivraison    nouvelle date de livraison
     */
    public CommandeUpdateInput(String adresseLivraison, String dateLivraison) {
        this.adresseLivraison = adresseLivraison;
        this.dateLivraison = dateLivraison;
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
}
