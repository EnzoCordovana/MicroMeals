<?php

namespace gui;

include_once "View.php";

class ViewPlats extends View
{
    public function __construct($layout, $presenter)
    {
        parent::__construct($layout);

        $this->title = 'MicroMeals - Plats';
        $this->content = $presenter->getAllPlatsHTML();
    }
}
