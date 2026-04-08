<?php

namespace service;

class MenuChecking
{
    protected $menusTxt;

    public function getMenusTxt()
    {
        return $this->menusTxt;
    }

    public function getAllMenus($data)
    {
        $menus = $data->getAllMenus();

        $this->menusTxt = array();
        foreach ($menus as $menu) {
            $plats = array();
            foreach ($menu->getPlats() as $plat) {
                $plats[] = [
                    'id' => $plat->getId(),
                    'nom' => $plat->getNom(),
                    'prix' => $plat->getPrix()
                ];
            }

            $this->menusTxt[] = [
                'id' => $menu->getId(),
                'nom' => $menu->getNom(),
                'createurId' => $menu->getCreateurId(),
                'createurNom' => $menu->getCreateurNom(),
                'dateCreation' => $menu->getDateCreation(),
                'dateMiseAJour' => $menu->getDateMiseAJour(),
                'plats' => $plats,
                'prixTotal' => $menu->getPrixTotal()
            ];
        }
    }

    public function getMenu($id, $data)
    {
        $menu = $data->getMenu($id);

        $plats = array();
        foreach ($menu->getPlats() as $plat) {
            $plats[] = [
                'id' => $plat->getId(),
                'nom' => $plat->getNom(),
                'prix' => $plat->getPrix()
            ];
        }

        $this->menusTxt = array();
        $this->menusTxt[] = [
            'id' => $menu->getId(),
            'nom' => $menu->getNom(),
            'createurId' => $menu->getCreateurId(),
            'createurNom' => $menu->getCreateurNom(),
            'dateCreation' => $menu->getDateCreation(),
            'dateMiseAJour' => $menu->getDateMiseAJour(),
            'plats' => $plats,
            'prixTotal' => $menu->getPrixTotal()
        ];
    }

    public function createMenu($nom, $createurId, $platsIds, $data)
    {
        return $data->createMenu($nom, $createurId, $platsIds);
    }

    public function updateMenu($id, $nom, $platsIds, $data)
    {
        return $data->updateMenu($id, $nom, $platsIds);
    }

    public function deleteMenu($id, $data)
    {
        $data->deleteMenu($id);
    }
}
