<?php

namespace control;

class Presenter
{
    protected $platChecking;
    protected $menuChecking;
    protected $commandeChecking;

    public function __construct($platChecking, $menuChecking, $commandeChecking)
    {
        $this->platChecking = $platChecking;
        $this->menuChecking = $menuChecking;
        $this->commandeChecking = $commandeChecking;
    }

    public function getAllPlatsHTML()
    {
        $content = null;
        if ($this->platChecking->getPlatsTxt() != null) {
            $content = '<h1>Nos Plats</h1>';
            $content .= '<table border="1" cellpadding="8" cellspacing="0">';
            $content .= '<tr><th>Nom</th><th>Description</th><th>Prix</th></tr>';
            foreach ($this->platChecking->getPlatsTxt() as $plat) {
                $content .= '<tr>';
                $content .= '<td>' . htmlspecialchars($plat['nom']) . '</td>';
                $content .= '<td>' . htmlspecialchars($plat['description']) . '</td>';
                $content .= '<td>' . number_format($plat['prix'], 2) . ' &euro;</td>';
                $content .= '</tr>';
            }
            $content .= '</table>';
        }
        return $content;
    }

    public function getAllMenusHTML()
    {
        $content = null;
        if ($this->menuChecking->getMenusTxt() != null) {
            $content = '<h1>Nos Menus</h1>';
            $content .= '<p><a href="/index.php/menus/composer">Composer un nouveau menu</a></p>';
            foreach ($this->menuChecking->getMenusTxt() as $menu) {
                $content .= '<div class="menu">';
                $content .= '<h2>' . htmlspecialchars($menu['nom']) . '</h2>';
                $content .= '<p>Cr&eacute;&eacute; par : ' . htmlspecialchars($menu['createurNom']) . '</p>';
                $content .= '<p>Date de cr&eacute;ation : ' . htmlspecialchars($menu['dateCreation']) . '</p>';
                $content .= '<p>Derni&egrave;re mise &agrave; jour : ' . htmlspecialchars($menu['dateMiseAJour']) . '</p>';
                $content .= '<ul>';
                foreach ($menu['plats'] as $plat) {
                    $content .= '<li>' . htmlspecialchars($plat['nom']) . ' - ' . number_format($plat['prix'], 2) . ' &euro;</li>';
                }
                $content .= '</ul>';
                $content .= '<p><strong>Prix total : ' . number_format($menu['prixTotal'], 2) . ' &euro;</strong></p>';
                $content .= '<a href="/index.php/menus/modifier?id=' . $menu['id'] . '">Modifier</a>';
                $content .= ' | <a href="/index.php/menus/supprimer?id=' . $menu['id'] . '" onclick="return confirm(\'Supprimer ce menu ?\')">Supprimer</a>';
                $content .= '</div><hr>';
            }
        }
        return $content;
    }

    public function getMenuFormHTML($menuId = null)
    {
        $plats = $this->platChecking->getPlatsTxt();
        $menuNom = '';
        $selectedPlats = [];
        $createurId = '';

        if ($menuId !== null && $this->menuChecking->getMenusTxt() != null) {
            $menu = $this->menuChecking->getMenusTxt()[0];
            $menuNom = $menu['nom'];
            $createurId = $menu['createurId'];
            foreach ($menu['plats'] as $p) {
                $selectedPlats[] = $p['id'];
            }
        }

        $action = ($menuId !== null) ? '/index.php/menus/modifier' : '/index.php/menus/composer';

        $content = '<h1>' . ($menuId !== null ? 'Modifier le menu' : 'Composer un nouveau menu') . '</h1>';
        $content .= '<form method="post" action="' . $action . '">';

        if ($menuId !== null) {
            $content .= '<input type="hidden" name="id" value="' . intval($menuId) . '">';
        }

        $content .= '<label for="nom">Nom du menu :</label><br>';
        $content .= '<input type="text" name="nom" id="nom" value="' . htmlspecialchars($menuNom) . '" required><br><br>';

        if ($menuId === null) {
            $content .= '<label for="createurId">Votre identifiant utilisateur :</label><br>';
            $content .= '<input type="number" name="createurId" id="createurId" value="' . htmlspecialchars($createurId) . '" required><br><br>';
        }

        $content .= '<fieldset><legend>S&eacute;lectionnez les plats :</legend>';
        if ($plats != null) {
            foreach ($plats as $plat) {
                $checked = in_array($plat['id'], $selectedPlats) ? ' checked' : '';
                $content .= '<label>';
                $content .= '<input type="checkbox" name="platsIds[]" value="' . $plat['id'] . '"' . $checked . '> ';
                $content .= htmlspecialchars($plat['nom']) . ' (' . number_format($plat['prix'], 2) . ' &euro;)';
                $content .= '</label><br>';
            }
        }
        $content .= '</fieldset><br>';

        $content .= '<input type="submit" value="' . ($menuId !== null ? 'Modifier' : 'Cr&eacute;er') . '">';
        $content .= ' <a href="/index.php/menus">Annuler</a>';
        $content .= '</form>';

        return $content;
    }

    public function getAllCommandesHTML()
    {
        $content = null;
        if ($this->commandeChecking->getCommandesTxt() != null) {
            $content = '<h1>Commandes</h1>';
            $content .= '<p><a href="/index.php/commandes/nouvelle">Passer une commande</a></p>';
            foreach ($this->commandeChecking->getCommandesTxt() as $commande) {
                $content .= '<div class="commande">';
                $content .= '<h2>Commande #' . $commande['id'] . '</h2>';
                $content .= '<p>Date : ' . htmlspecialchars($commande['dateCommande']) . '</p>';
                $content .= '<p>Livraison le : ' . htmlspecialchars($commande['dateLivraison']) . '</p>';
                $content .= '<p>Adresse : ' . htmlspecialchars($commande['adresseLivraison']) . '</p>';
                $content .= '<table border="1" cellpadding="8" cellspacing="0">';
                $content .= '<tr><th>Menu</th><th>Quantit&eacute;</th><th>Prix unitaire</th><th>Prix ligne</th></tr>';
                foreach ($commande['lignes'] as $ligne) {
                    $content .= '<tr>';
                    $content .= '<td>' . htmlspecialchars($ligne['menuNom']) . '</td>';
                    $content .= '<td>' . $ligne['quantite'] . '</td>';
                    $content .= '<td>' . number_format($ligne['prixUnitaire'], 2) . ' &euro;</td>';
                    $content .= '<td>' . number_format($ligne['prixLigne'], 2) . ' &euro;</td>';
                    $content .= '</tr>';
                }
                $content .= '</table>';
                $content .= '<p><strong>Total : ' . number_format($commande['prixTotal'], 2) . ' &euro;</strong></p>';
                $content .= '</div><hr>';
            }
        } else {
            $content = '<h1>Commandes</h1>';
            $content .= '<p>Aucune commande pour le moment.</p>';
            $content .= '<p><a href="/index.php/commandes/nouvelle">Passer une commande</a></p>';
        }
        return $content;
    }

    public function getCommandeFormHTML()
    {
        $menus = $this->menuChecking->getMenusTxt();

        $content = '<h1>Passer une commande</h1>';
        $content .= '<form method="post" action="/index.php/commandes/nouvelle">';

        $content .= '<label for="abonneId">Votre identifiant abonn&eacute; :</label><br>';
        $content .= '<input type="number" name="abonneId" id="abonneId" required><br><br>';

        $content .= '<label for="adresseLivraison">Adresse de livraison :</label><br>';
        $content .= '<input type="text" name="adresseLivraison" id="adresseLivraison" size="60" required><br><br>';

        $content .= '<label for="dateLivraison">Date de livraison :</label><br>';
        $content .= '<input type="date" name="dateLivraison" id="dateLivraison" required><br><br>';

        $content .= '<fieldset><legend>Menus et quantit&eacute;s :</legend>';
        if ($menus != null) {
            foreach ($menus as $menu) {
                $content .= '<div>';
                $content .= '<label>' . htmlspecialchars($menu['nom']) . ' (' . number_format($menu['prixTotal'], 2) . ' &euro;)</label> ';
                $content .= 'Quantit&eacute; : <input type="number" name="quantites[' . $menu['id'] . ']" value="0" min="0" style="width:60px">';
                $content .= '</div><br>';
            }
        }
        $content .= '</fieldset><br>';

        $content .= '<input type="submit" value="Valider la commande">';
        $content .= ' <a href="/index.php/commandes">Annuler</a>';
        $content .= '</form>';

        return $content;
    }
}
