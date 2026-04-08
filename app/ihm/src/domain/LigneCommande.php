<?php

namespace domain;

class LigneCommande
{
    protected $menuId;
    protected $menuNom;
    protected $quantite;
    protected $prixUnitaire;
    protected $prixLigne;

    public function __construct($menuId, $menuNom, $quantite, $prixUnitaire, $prixLigne)
    {
        $this->menuId = $menuId;
        $this->menuNom = $menuNom;
        $this->quantite = $quantite;
        $this->prixUnitaire = $prixUnitaire;
        $this->prixLigne = $prixLigne;
    }

    public function getMenuId()
    {
        return $this->menuId;
    }

    public function getMenuNom()
    {
        return $this->menuNom;
    }

    public function getQuantite()
    {
        return $this->quantite;
    }

    public function getPrixUnitaire()
    {
        return $this->prixUnitaire;
    }

    public function getPrixLigne()
    {
        return $this->prixLigne;
    }
}
