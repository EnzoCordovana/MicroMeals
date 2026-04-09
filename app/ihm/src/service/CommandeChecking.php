<?php

namespace service;

class CommandeChecking
{
    protected $commandesTxt;

    public function getCommandesTxt()
    {
        return $this->commandesTxt;
    }

    public function getAllCommandes($data)
    {
        $commandes = $data->getAllCommandes();

        $this->commandesTxt = array();
        foreach ($commandes as $commande) {
            $lignes = array();
            foreach ($commande->getLignes() as $ligne) {
                $lignes[] = [
                    'menuId' => $ligne->getMenuId(),
                    'menuNom' => $ligne->getMenuNom(),
                    'quantite' => $ligne->getQuantite(),
                    'prixUnitaire' => $ligne->getPrixUnitaire(),
                    'prixLigne' => $ligne->getPrixLigne()
                ];
            }

            $this->commandesTxt[] = [
                'id' => $commande->getId(),
                'abonneId' => $commande->getAbonneId(),
                'dateCommande' => $commande->getDateCommande(),
                'adresseLivraison' => $commande->getAdresseLivraison(),
                'dateLivraison' => $commande->getDateLivraison(),
                'lignes' => $lignes,
                'prixTotal' => $commande->getPrixTotal()
            ];
        }
    }

    public function getCommande($id, $data)
    {
        $commande = $data->getCommande($id);

        $lignes = array();
        foreach ($commande->getLignes() as $ligne) {
            $lignes[] = [
                'menuId' => $ligne->getMenuId(),
                'menuNom' => $ligne->getMenuNom(),
                'quantite' => $ligne->getQuantite(),
                'prixUnitaire' => $ligne->getPrixUnitaire(),
                'prixLigne' => $ligne->getPrixLigne()
            ];
        }

        $this->commandesTxt = array();
        $this->commandesTxt[] = [
            'id' => $commande->getId(),
            'abonneId' => $commande->getAbonneId(),
            'dateCommande' => $commande->getDateCommande(),
            'adresseLivraison' => $commande->getAdresseLivraison(),
            'dateLivraison' => $commande->getDateLivraison(),
            'lignes' => $lignes,
            'prixTotal' => $commande->getPrixTotal()
        ];
    }

    public function createCommande($abonneId, $adresseLivraison, $dateLivraison, $lignes, $data)
    {
        return $data->createCommande($abonneId, $adresseLivraison, $dateLivraison, $lignes);
    }
}
