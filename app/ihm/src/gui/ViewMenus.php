<?php

namespace gui;

include_once "View.php";

class ViewMenus extends View
{
    public function __construct($layout, $presenter)
    {
        parent::__construct($layout);

        $this->title = 'MicroMeals - Menus';
        $this->content = $presenter->getAllMenusHTML();
    }
}
