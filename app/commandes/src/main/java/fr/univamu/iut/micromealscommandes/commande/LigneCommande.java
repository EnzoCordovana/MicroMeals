package fr.univamu.iut.micromealscommandes.commande;

/**
 * Entite representant une ligne d'une commande, associee a un menu particulier
 */
public class LigneCommande {

    protected int menuId;
    protected String menuNom;
    protected int quantite;
    protected double prixUnitaire;
    protected double prixLigne;

    public LigneCommande() {
    }

    /**
     * Crée une ligne de commande et calcule automatiquement le prix total de la ligne.
     *
     * @param menuId       identifiant du menu
     * @param menuNom      nom du menu
     * @param quantite     quantite commandee
     * @param prixUnitaire prix unitaire du menu
     */
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
