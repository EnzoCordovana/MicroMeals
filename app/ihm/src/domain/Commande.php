<?php

namespace domain;

class Commande
{
    protected $id;
    protected $abonneId;
    protected $dateCommande;
    protected $adresseLivraison;
    protected $dateLivraison;
    protected $lignes;
    protected $prixTotal;

    public function __construct($id, $abonneId, $dateCommande, $adresseLivraison, $dateLivraison, $lignes, $prixTotal)
    {
        $this->id = $id;
        $this->abonneId = $abonneId;
        $this->dateCommande = $dateCommande;
        $this->adresseLivraison = $adresseLivraison;
        $this->dateLivraison = $dateLivraison;
        $this->lignes = $lignes;
        $this->prixTotal = $prixTotal;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getAbonneId()
    {
        return $this->abonneId;
    }

    public function getDateCommande()
    {
        return $this->dateCommande;
    }

    public function getAdresseLivraison()
    {
        return $this->adresseLivraison;
    }

    public function getDateLivraison()
    {
        return $this->dateLivraison;
    }

    public function getLignes()
    {
        return $this->lignes;
    }

    public function getPrixTotal()
    {
        return $this->prixTotal;
    }
}
