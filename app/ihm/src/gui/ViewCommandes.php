<?php

namespace gui;

include_once "View.php";

class ViewCommandes extends View
{
    public function __construct($layout, $presenter)
    {
        parent::__construct($layout);

        $this->title = 'MicroMeals - Commandes';
        $this->content = $presenter->getAllCommandesHTML();
    }
}
