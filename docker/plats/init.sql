USE db_plats;

CREATE USER IF NOT EXISTS 'plats_user'@'%' IDENTIFIED BY 'plats_pass';
GRANT ALL PRIVILEGES ON db_plats.* TO 'plats_user'@'%';
FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS Food (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nom         VARCHAR(255) NOT NULL,
    description TEXT,
    prix        DOUBLE NOT NULL
);

INSERT INTO Food (nom, description, prix) VALUES
    ('Salade nicoise', 'Salade composee avec thon, olives, oeufs durs et anchois', 8.50),
    ('Aioli provencal', 'Plat provencal : legumes vapeur, morue et sauce aioli', 12.00),
    ('Gratin dauphinois', 'Gratin de pommes de terre a la creme et a l ail', 9.00),
    ('Bouillabaisse', 'Soupe de poissons traditionnelle marseillaise', 15.50),
    ('Tian de legumes', 'Gratin de legumes du soleil au four', 7.50),
    ('Poulet roti', 'Poulet fermier roti aux herbes de Provence', 11.00),
    ('Mousse au chocolat', 'Mousse au chocolat noir maison', 5.00),
    ('Tarte tropezienne', 'Brioche garnie de creme a la fleur d oranger', 5.50);

CREATE TABLE IF NOT EXISTS Utilisateur (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nom      VARCHAR(255) NOT NULL,
    prenom   VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    adresse  TEXT         NOT NULL,
    password VARCHAR(255) NOT NULL DEFAULT ''
);

INSERT INTO Utilisateur (nom, prenom, email, adresse, password) VALUES
    ('Dupont',   'Marie',   'marie.dupont@email.fr',   '12 rue des Lilas, 13001 Marseille',       'marie123'),
    ('Martin',   'Jean',    'jean.martin@email.fr',    '5 avenue du Port, 13002 Marseille',        'jean123'),
    ('Petit',    'Sophie',  'sophie.petit@email.fr',   '8 boulevard Longchamp, 13001 Marseille',   'sophie123'),
    ('Bernard',  'Pierre',  'pierre.bernard@email.fr', '22 rue Saint-Ferreol, 13001 Marseille',    'pierre123'),
    ('Durand',   'Claire',  'claire.durand@email.fr',  '3 cours Julien, 13006 Marseille',          'claire123');