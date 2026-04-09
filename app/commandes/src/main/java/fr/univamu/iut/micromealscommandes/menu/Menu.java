package fr.univamu.iut.micromealscommandes;

public class Menu {

    protected int id;
    protected String nom;
    protected double prixTotal;

    public Menu() {
    }

    public Menu(int id, String nom, double prixTotal) {
        this.id = id;
        this.nom = nom;
        this.prixTotal = prixTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", nom='" + nom + '\'' + ", prixTotal=" + prixTotal + '}';
    }
}
