<?php

namespace control;

class Controllers
{
    public function platsAction($dataPlats, $platChecking)
    {
        $platChecking->getAllPlats($dataPlats);
    }

    public function platAction($id, $dataPlats, $platChecking)
    {
        $platChecking->getPlat($id, $dataPlats);
    }

    public function menusAction($dataMenus, $menuChecking)
    {
        $menuChecking->getAllMenus($dataMenus);
    }

    public function menuAction($id, $dataMenus, $menuChecking)
    {
        $menuChecking->getMenu($id, $dataMenus);
    }

    public function createMenuAction($nom, $createurId, $platsIds, $dataMenus, $menuChecking)
    {
        $menuChecking->createMenu($nom, $createurId, $platsIds, $dataMenus);
    }

    public function updateMenuAction($id, $nom, $platsIds, $dataMenus, $menuChecking)
    {
        $menuChecking->updateMenu($id, $nom, $platsIds, $dataMenus);
    }

    public function deleteMenuAction($id, $dataMenus, $menuChecking)
    {
        $menuChecking->deleteMenu($id, $dataMenus);
    }

    public function commandesAction($dataCommandes, $commandeChecking)
    {
        $commandeChecking->getAllCommandes($dataCommandes);
    }

    public function commandeAction($id, $dataCommandes, $commandeChecking)
    {
        $commandeChecking->getCommande($id, $dataCommandes);
    }

    public function createCommandeAction($abonneId, $adresseLivraison, $dateLivraison, $lignes, $dataCommandes, $commandeChecking)
    {
        $commandeChecking->createCommande($abonneId, $adresseLivraison, $dateLivraison, $lignes, $dataCommandes);
    }
}
