<?php

namespace domain;

class Menu
{
    protected $id;
    protected $nom;
    protected $createurId;
    protected $createurNom;
    protected $dateCreation;
    protected $dateMiseAJour;
    protected $plats;
    protected $prixTotal;

    public function __construct($id, $nom, $createurId, $createurNom, $dateCreation, $dateMiseAJour, $plats, $prixTotal)
    {
        $this->id = $id;
        $this->nom = $nom;
        $this->createurId = $createurId;
        $this->createurNom = $createurNom;
        $this->dateCreation = $dateCreation;
        $this->dateMiseAJour = $dateMiseAJour;
        $this->plats = $plats;
        $this->prixTotal = $prixTotal;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getNom()
    {
        return $this->nom;
    }

    public function getCreateurId()
    {
        return $this->createurId;
    }

    public function getCreateurNom()
    {
        return $this->createurNom;
    }

    public function getDateCreation()
    {
        return $this->dateCreation;
    }

    public function getDateMiseAJour()
    {
        return $this->dateMiseAJour;
    }

    public function getPlats()
    {
        return $this->plats;
    }

    public function getPrixTotal()
    {
        return $this->prixTotal;
    }
}
