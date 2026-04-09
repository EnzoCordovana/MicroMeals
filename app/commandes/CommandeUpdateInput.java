package fr.univamu.iut.micromealscommandes;

public class CommandeUpdateInput {

    protected String adresseLivraison;
    protected String dateLivraison;

    public CommandeUpdateInput() {
    }

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
