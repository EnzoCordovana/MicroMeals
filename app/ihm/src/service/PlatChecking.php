<?php

namespace service;

class PlatChecking
{
    protected $platsTxt;

    public function getPlatsTxt()
    {
        return $this->platsTxt;
    }

    public function getAllPlats($data)
    {
        $plats = $data->getAllPlats();

        $this->platsTxt = array();
        foreach ($plats as $plat) {
            $this->platsTxt[] = [
                'id' => $plat->getId(),
                'nom' => $plat->getNom(),
                'description' => $plat->getDescription(),
                'prix' => $plat->getPrix()
            ];
        }
    }

    public function getPlat($id, $data)
    {
        $plat = $data->getPlat($id);

        $this->platsTxt = array();
        $this->platsTxt[] = [
            'id' => $plat->getId(),
            'nom' => $plat->getNom(),
            'description' => $plat->getDescription(),
            'prix' => $plat->getPrix()
        ];
    }

    public function createPlat($nom, $description, $prix, $data)
    {
        return $data->createPlat($nom, $description, $prix);
    }

    public function updatePlat($id, $nom, $description, $prix, $data)
    {
        return $data->updatePlat($id, $nom, $description, $prix);
    }

    public function deletePlat($id, $data)
    {
        $data->deletePlat($id);
    }
}
