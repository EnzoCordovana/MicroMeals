<?php

namespace service;

interface PlatAccessInterface
{
    public function getAllPlats();

    public function getPlat($id);

    public function createPlat($nom, $description, $prix);

    public function updatePlat($id, $nom, $description, $prix);

    public function deletePlat($id);
}
