<?php

namespace gateway;

use service\MenuAccessInterface;
include_once "service/MenuAccessInterface.php";

use domain\Menu;
include_once "domain/Menu.php";

use domain\Plat;
include_once "domain/Plat.php";

class MenuApiAccess implements MenuAccessInterface
{
    protected $baseUrl;

    public function __construct($baseUrl)
    {
        $this->baseUrl = $baseUrl;
    }

    public function getAllMenus()
    {
        $json = file_get_contents($this->baseUrl . '/menus');
        $data = json_decode($json, true);

        $menus = array();
        foreach ($data as $item) {
            $plats = array();
            foreach ($item['plats'] as $p) {
                $plats[] = new Plat($p['id'], $p['nom'], null, $p['prix']);
            }

            $menus[] = new Menu(
                $item['id'], $item['nom'],
                $item['createurId'], $item['createurNom'],
                $item['dateCreation'], $item['dateMiseAJour'],
                $plats, $item['prixTotal']
            );
        }

        return $menus;
    }

    public function getMenu($id)
    {
        $id = intval($id);
        $json = file_get_contents($this->baseUrl . '/menus/' . $id);
        $item = json_decode($json, true);

        $plats = array();
        foreach ($item['plats'] as $p) {
            $plats[] = new Plat($p['id'], $p['nom'], null, $p['prix']);
        }

        return new Menu(
            $item['id'], $item['nom'],
            $item['createurId'], $item['createurNom'],
            $item['dateCreation'], $item['dateMiseAJour'],
            $plats, $item['prixTotal']
        );
    }

    public function createMenu($nom, $createurId, $platsIds)
    {
        $body = json_encode([
            'nom' => $nom,
            'createurId' => intval($createurId),
            'platsIds' => $platsIds
        ]);
        $context = stream_context_create(['http' => [
            'method' => 'POST',
            'header' => 'Content-Type: application/json',
            'content' => $body
        ]]);
        $json = file_get_contents($this->baseUrl . '/menus', false, $context);
        return json_decode($json, true);
    }

    public function updateMenu($id, $nom, $platsIds)
    {
        $id = intval($id);
        $body = json_encode([
            'nom' => $nom,
            'platsIds' => $platsIds
        ]);
        $context = stream_context_create(['http' => [
            'method' => 'PUT',
            'header' => 'Content-Type: application/json',
            'content' => $body
        ]]);
        $json = file_get_contents($this->baseUrl . '/menus/' . $id, false, $context);
        return json_decode($json, true);
    }

    public function deleteMenu($id)
    {
        $id = intval($id);
        $context = stream_context_create(['http' => ['method' => 'DELETE']]);
        file_get_contents($this->baseUrl . '/menus/' . $id, false, $context);
    }
}
