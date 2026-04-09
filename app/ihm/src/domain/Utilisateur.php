<?php

namespace domain;

class Utilisateur
{
    protected $id;
    protected $nom;
    protected $prenom;
    protected $email;
    protected $adresse;

    public function __construct($id, $nom, $prenom, $email, $adresse)
    {
        $this->id = $id;
        $this->nom = $nom;
        $this->prenom = $prenom;
        $this->email = $email;
        $this->adresse = $adresse;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getNom()
    {
        return $this->nom;
    }

    public function getPrenom()
    {
        return $this->prenom;
    }

    public function getEmail()
    {
        return $this->email;
    }

    public function getAdresse()
    {
        return $this->adresse;
    }
}
