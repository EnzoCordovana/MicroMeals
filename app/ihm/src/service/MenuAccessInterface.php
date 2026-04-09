<?php

namespace service;

interface MenuAccessInterface
{
    public function getAllMenus();

    public function getMenu($id);

    public function createMenu($nom, $createurId, $platsIds);

    public function updateMenu($id, $nom, $platsIds);

    public function deleteMenu($id);
}
