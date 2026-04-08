<?php

namespace gui;

include_once "View.php";

class ViewMenuComposer extends View
{
    public function __construct($layout, $presenter, $menuId = null)
    {
        parent::__construct($layout);

        if ($menuId !== null) {
            $this->title = 'MicroMeals - Modifier un menu';
            $this->content = $presenter->getMenuFormHTML($menuId);
        } else {
            $this->title = 'MicroMeals - Composer un menu';
            $this->content = $presenter->getMenuFormHTML();
        }
    }
}
