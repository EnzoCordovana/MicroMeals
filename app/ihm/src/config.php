<?php

// Charge les variables d'environnement depuis le fichier .env
// QUI NE DOIT PAS ETRE COMMIT
$env = @parse_ini_file(__DIR__ . '/.env');

define('API_PLATS_URL', getenv('API_PLATS_URL') ?: ($env['API_PLATS_URL'] ?? 'http://localhost:3003'));
define('API_MENUS_URL', getenv('API_MENUS_URL') ?: ($env['API_MENUS_URL'] ?? 'http://localhost:3004'));
define('API_COMMANDES_URL', getenv('API_COMMANDES_URL') ?: ($env['API_COMMANDES_URL'] ?? 'http://localhost:3005'));
?>
