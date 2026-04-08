<?php

namespace gateway;

use service\CommandeAccessInterface;
include_once "service/CommandeAccessInterface.php";

use domain\Commande;
include_once "domain/Commande.php";

use domain\LigneCommande;
include_once "domain/LigneCommande.php";

class CommandeApiAccess implements CommandeAccessInterface
{
    protected $baseUrl;

    public function __construct($baseUrl)
    {
        $this->baseUrl = $baseUrl;
    }

    public function getAllCommandes()
    {
        $json = file_get_contents($this->baseUrl . '/commandes');
        $data = json_decode($json, true);

        $commandes = array();
        foreach ($data as $item) {
            $lignes = array();
            foreach ($item['lignes'] as $l) {
                $lignes[] = new LigneCommande(
                    $l['menuId'], $l['menuNom'],
                    $l['quantite'], $l['prixUnitaire'], $l['prixLigne']
                );
            }

            $commandes[] = new Commande(
                $item['id'], $item['abonneId'],
                $item['dateCommande'], $item['adresseLivraison'],
                $item['dateLivraison'], $lignes, $item['prixTotal']
            );
        }

        return $commandes;
    }

    public function getCommande($id)
    {
        $id = intval($id);
        $json = file_get_contents($this->baseUrl . '/commandes/' . $id);
        $item = json_decode($json, true);

        $lignes = array();
        foreach ($item['lignes'] as $l) {
            $lignes[] = new LigneCommande(
                $l['menuId'], $l['menuNom'],
                $l['quantite'], $l['prixUnitaire'], $l['prixLigne']
            );
        }

        return new Commande(
            $item['id'], $item['abonneId'],
            $item['dateCommande'], $item['adresseLivraison'],
            $item['dateLivraison'], $lignes, $item['prixTotal']
        );
    }

    public function createCommande($abonneId, $adresseLivraison, $dateLivraison, $lignes)
    {
        $body = json_encode([
            'abonneId' => intval($abonneId),
            'adresseLivraison' => $adresseLivraison,
            'dateLivraison' => $dateLivraison,
            'lignes' => $lignes
        ]);
        $context = stream_context_create(['http' => [
            'method' => 'POST',
            'header' => 'Content-Type: application/json',
            'content' => $body
        ]]);
        $json = file_get_contents($this->baseUrl . '/commandes', false, $context);
        return json_decode($json, true);
    }
}
