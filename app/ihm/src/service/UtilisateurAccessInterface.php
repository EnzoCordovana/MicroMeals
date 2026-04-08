<?php

namespace service;

interface UtilisateurAccessInterface
{
    public function getAllUtilisateurs();

    public function getUtilisateur($id);

    public function createUtilisateur($nom, $prenom, $email, $adresse);

    public function updateUtilisateur($id, $nom, $prenom, $email, $adresse);

    public function deleteUtilisateur($id);
}
