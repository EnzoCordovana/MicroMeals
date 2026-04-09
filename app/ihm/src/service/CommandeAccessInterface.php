<?php

namespace service;

interface CommandeAccessInterface
{
    public function getAllCommandes();

    public function getCommande($id);

    public function createCommande($abonneId, $adresseLivraison, $dateLivraison, $lignes);
}
