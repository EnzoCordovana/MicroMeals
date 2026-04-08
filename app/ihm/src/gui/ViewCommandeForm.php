<?php

namespace gui;

include_once "View.php";

class ViewCommandeForm extends View
{
    public function __construct($layout, $presenter)
    {
        parent::__construct($layout);

        $this->title = 'MicroMeals - Passer une commande';
        $this->content = $presenter->getCommandeFormHTML();
    }
}
