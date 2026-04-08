<?php

// Le document root Apache est /var/www/src/public
// On remonte dans /var/www/src pour les includes
chdir(dirname(__DIR__));

require_once 'config.php';

// charge les bibliothèques
include_once 'gateway/PlatApiAccess.php';
include_once 'gateway/UtilisateurApiAccess.php';
include_once 'gateway/MenuApiAccess.php';
include_once 'gateway/CommandeApiAccess.php';

include_once 'control/Controllers.php';
include_once 'control/Presenter.php';

include_once 'service/PlatChecking.php';
include_once 'service/UtilisateurChecking.php';
include_once 'service/MenuChecking.php';
include_once 'service/CommandeChecking.php';

include_once 'gui/Layout.php';
include_once 'gui/ViewPlats.php';
include_once 'gui/ViewMenus.php';
include_once 'gui/ViewMenuComposer.php';
include_once 'gui/ViewCommandes.php';
include_once 'gui/ViewCommandeForm.php';
include_once 'gui/ViewError.php';

use gui\{ViewPlats, ViewMenus, ViewMenuComposer, ViewCommandes, ViewCommandeForm, ViewError, Layout};
use control\{Controllers, Presenter};
use gateway\{PlatApiAccess, UtilisateurApiAccess, MenuApiAccess, CommandeApiAccess};
use service\{PlatChecking, UtilisateurChecking, MenuChecking, CommandeChecking};

// construction des accès aux APIs (gateway)
$dataPlats = new PlatApiAccess(API_PLATS_URL);
$dataUtilisateurs = new UtilisateurApiAccess(API_PLATS_URL);
$dataMenus = new MenuApiAccess(API_MENUS_URL);
$dataCommandes = new CommandeApiAccess(API_COMMANDES_URL);

// initialisation des services
$platChecking = new PlatChecking();
$utilisateurChecking = new UtilisateurChecking();
$menuChecking = new MenuChecking();
$commandeChecking = new CommandeChecking();

// initialisation du controller
$controller = new Controllers();

// initialisation du presenter
$presenter = new Presenter($platChecking, $menuChecking, $commandeChecking);

// chemin de l'URL demandée au navigateur
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// route la requête en interne
if ('/' == $uri || '/index.php' == $uri || '/index.php/plats' == $uri) {
    // affichage de tous les plats
    $controller->platsAction($dataPlats, $platChecking);

    $layout = new Layout("gui/layout.html");
    $vuePlats = new ViewPlats($layout, $presenter);
    $vuePlats->display();

} elseif ('/index.php/menus' == $uri) {
    // affichage de tous les menus
    $controller->menusAction($dataMenus, $menuChecking);

    $layout = new Layout("gui/layout.html");
    $vueMenus = new ViewMenus($layout, $presenter);
    $vueMenus->display();

} elseif ('/index.php/menus/composer' == $uri) {
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        // traitement du formulaire de création de menu
        $nom = $_POST['nom'];
        $createurId = $_POST['createurId'];
        $platsIds = isset($_POST['platsIds']) ? array_map('intval', $_POST['platsIds']) : [];

        $controller->createMenuAction($nom, $createurId, $platsIds, $dataMenus, $menuChecking);
        header('Location: /index.php/menus');
        exit;
    }

    // affichage du formulaire de composition de menu
    $controller->platsAction($dataPlats, $platChecking);

    $layout = new Layout("gui/layout.html");
    $vueComposer = new ViewMenuComposer($layout, $presenter);
    $vueComposer->display();

} elseif ('/index.php/menus/modifier' == $uri) {
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        // traitement du formulaire de modification de menu
        $id = $_POST['id'];
        $nom = $_POST['nom'];
        $platsIds = isset($_POST['platsIds']) ? array_map('intval', $_POST['platsIds']) : [];

        $controller->updateMenuAction($id, $nom, $platsIds, $dataMenus, $menuChecking);
        header('Location: /index.php/menus');
        exit;
    }

    // affichage du formulaire de modification
    $menuId = isset($_GET['id']) ? intval($_GET['id']) : null;
    $controller->platsAction($dataPlats, $platChecking);
    $controller->menuAction($menuId, $dataMenus, $menuChecking);

    $layout = new Layout("gui/layout.html");
    $vueComposer = new ViewMenuComposer($layout, $presenter, $menuId);
    $vueComposer->display();

} elseif ('/index.php/menus/supprimer' == $uri && isset($_GET['id'])) {
    // suppression d'un menu
    $controller->deleteMenuAction(intval($_GET['id']), $dataMenus, $menuChecking);
    header('Location: /index.php/menus');
    exit;

} elseif ('/index.php/commandes' == $uri) {
    // affichage de toutes les commandes
    $controller->commandesAction($dataCommandes, $commandeChecking);

    $layout = new Layout("gui/layout.html");
    $vueCommandes = new ViewCommandes($layout, $presenter);
    $vueCommandes->display();

} elseif ('/index.php/commandes/nouvelle' == $uri) {
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        // traitement du formulaire de commande
        $abonneId = $_POST['abonneId'];
        $adresseLivraison = $_POST['adresseLivraison'];
        $dateLivraison = $_POST['dateLivraison'];

        // construction des lignes de commande (menus avec quantité > 0)
        $lignes = [];
        if (isset($_POST['quantites'])) {
            foreach ($_POST['quantites'] as $menuId => $quantite) {
                $quantite = intval($quantite);
                if ($quantite > 0) {
                    $lignes[] = [
                        'menuId' => intval($menuId),
                        'quantite' => $quantite
                    ];
                }
            }
        }

        $controller->createCommandeAction($abonneId, $adresseLivraison, $dateLivraison, $lignes, $dataCommandes, $commandeChecking);
        header('Location: /index.php/commandes');
        exit;
    }

    // affichage du formulaire de commande
    $controller->menusAction($dataMenus, $menuChecking);

    $layout = new Layout("gui/layout.html");
    $vueCommandeForm = new ViewCommandeForm($layout, $presenter);
    $vueCommandeForm->display();

} elseif ('/index.php/error' == $uri) {
    // affichage d'un message d'erreur
    $error = isset($_GET['msg']) ? $_GET['msg'] : 'Une erreur est survenue';

    $layout = new Layout("gui/layout.html");
    $vueError = new ViewError($layout, $error);
    $vueError->display();

} else {
    header('Status: 404 Not Found');
    echo '<html><body><h1>Page non trouvée</h1></body></html>';
}

?>
