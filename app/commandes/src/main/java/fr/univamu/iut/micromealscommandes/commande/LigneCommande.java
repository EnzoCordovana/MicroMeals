package fr.univamu.iut.micromealscommandes.commande;

public class LigneCommande {

    protected int menuId;
    protected String menuNom;
    protected int quantite;
    protected double prixUnitaire;
    protected double prixLigne;

    public LigneCommande() {
    }

    public LigneCommande(int menuId, String menuNom, int quantite, double prixUnitaire) {
        this.menuId = menuId;
        this.menuNom = menuNom;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.prixLigne = prixUnitaire * quantite;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuNom() {
        return menuNom;
    }

    public void setMenuNom(String menuNom) {
        this.menuNom = menuNom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getPrixLigne() {
        return prixLigne;
    }

    public void setPrixLigne(double prixLigne) {
        this.prixLigne = prixLigne;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "menuId=" + menuId + ", menuNom='" + menuNom + '\'' + ", quantite=" + quantite + ", prixUnitaire=" + prixUnitaire + ", prixLigne=" + prixLigne + '}';
    }
}
