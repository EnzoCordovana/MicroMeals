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