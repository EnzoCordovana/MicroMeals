<?php

namespace gateway;

use service\PlatAccessInterface;
include_once "service/PlatAccessInterface.php";

use domain\Plat;
include_once "domain/Plat.php";

class PlatApiAccess implements PlatAccessInterface
{
    protected $baseUrl;

    public function __construct($baseUrl)
    {
        $this->baseUrl = $baseUrl;
    }

    public function getAllPlats()
    {
        $json = file_get_contents($this->baseUrl . '/plats');
        $data = json_decode($json, true);

        $plats = array();
        foreach ($data as $item) {
            $plats[] = new Plat($item['id'], $item['nom'], $item['description'], $item['prix']);
        }

        return $plats;
    }

    public function getPlat($id)
    {
        $id = intval($id);
        $json = file_get_contents($this->baseUrl . '/plats/' . $id);
        $item = json_decode($json, true);

        return new Plat($item['id'], $item['nom'], $item['description'], $item['prix']);
    }

    public function createPlat($nom, $description, $prix)
    {
        $body = json_encode(['nom' => $nom, 'description' => $description, 'prix' => $prix]);
        $context = stream_context_create(['http' => [
            'method' => 'POST',
            'header' => 'Content-Type: application/json',
            'content' => $body
        ]]);
        $json = file_get_contents($this->baseUrl . '/plats', false, $context);
        return json_decode($json, true);
    }

    public function updatePlat($id, $nom, $description, $prix)
    {
        $id = intval($id);
        $body = json_encode(['nom' => $nom, 'description' => $description, 'prix' => $prix]);
        $context = stream_context_create(['http' => [
            'method' => 'PUT',
            'header' => 'Content-Type: application/json',
            'content' => $body
        ]]);
        $json = file_get_contents($this->baseUrl . '/plats/' . $id, false, $context);
        return json_decode($json, true);
    }

    public function deletePlat($id)
    {
        $id = intval($id);
        $context = stream_context_create(['http' => ['method' => 'DELETE']]);
        file_get_contents($this->baseUrl . '/plats/' . $id, false, $context);
    }
}
