<?php

namespace gateway;

use service\UtilisateurAccessInterface;
include_once "service/UtilisateurAccessInterface.php";

use domain\Utilisateur;
include_once "domain/Utilisateur.php";

class UtilisateurApiAccess implements UtilisateurAccessInterface
{
    protected $baseUrl;

    public function __construct($baseUrl)
    {
        $this->baseUrl = $baseUrl;
    }

    public function getAllUtilisateurs()
    {
        $json = file_get_contents($this->baseUrl . '/utilisateurs');
        $data = json_decode($json, true);

        $utilisateurs = array();
        foreach ($data as $item) {
            $utilisateurs[] = new Utilisateur(
                $item['id'], $item['nom'], $item['prenom'],
                $item['email'], $item['adresse']
            );
        }

        return $utilisateurs;
    }

    public function getUtilisateur($id)
    {
        $id = intval($id);
        $json = file_get_contents($this->baseUrl . '/utilisateurs/' . $id);
        $item = json_decode($json, true);

        return new Utilisateur(
            $item['id'], $item['nom'], $item['prenom'],
            $item['email'], $item['adresse']
        );
    }

    public function createUtilisateur($nom, $prenom, $email, $adresse)
    {
        $body = json_encode(['nom' => $nom, 'prenom' => $prenom, 'email' => $email, 'adresse' => $adresse]);
        $context = stream_context_create(['http' => [
            'method' => 'POST',
            'header' => 'Content-Type: application/json',
            'content' => $body
        ]]);
        $json = file_get_contents($this->baseUrl . '/utilisateurs', false, $context);
        return json_decode($json, true);
    }

    public function updateUtilisateur($id, $nom, $prenom, $email, $adresse)
    {
        $id = intval($id);
        $body = json_encode(['nom' => $nom, 'prenom' => $prenom, 'email' => $email, 'adresse' => $adresse]);
        $context = stream_context_create(['http' => [
            'method' => 'PUT',
            'header' => 'Content-Type: application/json',
            'content' => $body
        ]]);
        $json = file_get_contents($this->baseUrl . '/utilisateurs/' . $id, false, $context);
        return json_decode($json, true);
    }

    public function deleteUtilisateur($id)
    {
        $id = intval($id);
        $context = stream_context_create(['http' => ['method' => 'DELETE']]);
        file_get_contents($this->baseUrl . '/utilisateurs/' . $id, false, $context);
    }
}
