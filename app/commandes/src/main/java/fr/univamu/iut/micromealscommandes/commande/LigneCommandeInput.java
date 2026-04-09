package fr.univamu.iut.micromealscommandes.commande;

/**
 * Donnees d'entree representant une ligne de commande soumise par le client
 */
public class LigneCommandeInput {

    protected int menuId;
    protected int quantite;

    public LigneCommandeInput() {
    }

    /**
     * Cree une ligne d'entree avec le menu et la quantite souhaitee
     *
     * @param menuId   identifiant du menu
     * @param quantite quantite commandee
     */
    public LigneCommandeInput(int menuId, int quantite) {
        this.menuId = menuId;
        this.quantite = quantite;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
