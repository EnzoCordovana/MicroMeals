package fr.univamu.iut.micromealscommandes.menu;

/**
 * Interface definissant les operations de consultation des menus
 */
public interface MenuRepositoryInterface {

    /** Libere les ressources associees au depot */
    public void close();

    /**
     * Recupere un menu par son identifiant
     *
     * @param id identifiant du menu
     * @return le menu correspondant, ou {@code null} si introuvable
     */
    public Menu getMenu(int id);
}
