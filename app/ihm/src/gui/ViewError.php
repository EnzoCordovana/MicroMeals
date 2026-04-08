<?php

namespace gui;

include_once "View.php";

class ViewError extends View
{
    public function __construct($layout, $error)
    {
        parent::__construct($layout);

        $this->title = 'MicroMeals - Erreur';
        $this->content = '<h1>Erreur</h1><p>' . htmlspecialchars($error) . '</p>';
        $this->content .= '<p><a href="/index.php/plats">Retour aux plats</a></p>';
    }
}
