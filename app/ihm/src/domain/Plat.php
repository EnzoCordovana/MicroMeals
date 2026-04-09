<?php

namespace domain;

class Plat
{
    protected $id;
    protected $nom;
    protected $description;
    protected $prix;

    public function __construct($id, $nom, $description, $prix)
    {
        $this->id = $id;
        $this->nom = $nom;
        $this->description = $description;
        $this->prix = $prix;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getNom()
    {
        return $this->nom;
    }

    public function getDescription()
    {
        return $this->description;
    }

    public function getPrix()
    {
        return $this->prix;
    }
}
