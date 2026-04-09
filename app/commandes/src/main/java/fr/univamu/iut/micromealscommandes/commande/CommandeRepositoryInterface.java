package fr.univamu.iut.micromealscommandes.commande;

import java.util.ArrayList;

/**
 * Interface definissant les operations de persistance pour les commande
 */
public interface CommandeRepositoryInterface {

    /** Libere les ressources associées au depot. */
    public void close();

    /**
     * Recupere une commande par son identifiant
     *
     * @param id identifiant de la commande
     * @return la commande correspondante, ou {@code null} si introuvable
     */
    public Commande getCommande(int id);

    /**
     * Retourne toutes les commandes enregistrees
     *
     * @return liste de toutes les commandes
     */
    public ArrayList<Commande> getAllCommandes();

    /**
     * Retourne les commandes associees a un abonne
     *
     * @param abonneId identifiant de l'abonne
     * @return liste des commandes de l'abonne
     */
    public ArrayList<Commande> getCommandesByAbonneId(int abonneId);

    /**
     * Enregistre une nouvelle commande
     *
     * @param commande la commande a persister
     * @return l'identifiant genere, ou {@code -1} en cas d'echec
     */
    public int createCommande(Commande commande);

    /**
     * Met a jour l'adresse et la date de livraison d'une commande
     *
     * @param id               identifiant de la commande
     * @param adresseLivraison nouvelle adresse de livraison
     * @param dateLivraison    nouvelle date de livraison
     * @return {@code true} si la mise a jour a reussi
     */
    public boolean updateCommande(int id, String adresseLivraison, String dateLivraison);

    /**
     * Supprime une commande par son identifiant
     *
     * @param id identifiant de la commande
     * @return {@code true} si la suppression a reussi
     */
    public boolean deleteCommande(int id);
}
