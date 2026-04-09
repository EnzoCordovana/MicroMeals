package fr.univamu.iut.micromealscommandes;

public class LigneCommandeInput {

    protected int menuId;
    protected int quantite;

    public LigneCommandeInput() {
    }

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
