<?php

namespace service;

class UtilisateurChecking
{
    protected $utilisateursTxt;

    public function getUtilisateursTxt()
    {
        return $this->utilisateursTxt;
    }

    public function getAllUtilisateurs($data)
    {
        $utilisateurs = $data->getAllUtilisateurs();

        $this->utilisateursTxt = array();
        foreach ($utilisateurs as $u) {
            $this->utilisateursTxt[] = [
                'id' => $u->getId(),
                'nom' => $u->getNom(),
                'prenom' => $u->getPrenom(),
                'email' => $u->getEmail(),
                'adresse' => $u->getAdresse()
            ];
        }
    }

    public function getUtilisateur($id, $data)
    {
        $u = $data->getUtilisateur($id);

        $this->utilisateursTxt = array();
        $this->utilisateursTxt[] = [
            'id' => $u->getId(),
            'nom' => $u->getNom(),
            'prenom' => $u->getPrenom(),
            'email' => $u->getEmail(),
            'adresse' => $u->getAdresse()
        ];
    }

    public function createUtilisateur($nom, $prenom, $email, $adresse, $data)
    {
        return $data->createUtilisateur($nom, $prenom, $email, $adresse);
    }

    public function updateUtilisateur($id, $nom, $prenom, $email, $adresse, $data)
    {
        return $data->updateUtilisateur($id, $nom, $prenom, $email, $adresse);
    }

    public function deleteUtilisateur($id, $data)
    {
        $data->deleteUtilisateur($id);
    }
}
