#CREATION DES TABLES
CREATE TABLE Entraineur (
                             id INTEGER PRIMARY KEY AUTO_INCREMENT,
                             nom VARCHAR(255),
                             argents INTEGER
);

CREATE TABLE EspeceMonstre (
                            id INTEGER PRIMARY KEY AUTO_INCREMENT,
                            nom VARCHAR(255),
                            type VARCHAR(255),
                            baseAttaque INTEGER,
                            baseDefense INTEGER,
                            baseVitesse INTEGER,
                            baseAttaqueSpe INTEGER,
                            baseDefenseSpe INTEGER,
                            basePv INTEGER,
                            modAttaque DOUBLE,
                            modDefense DOUBLE,
                            modVitesse DOUBLE,
                            modAttaqueSpe DOUBLE,
                            modDefenseSpe DOUBLE,
                            modPv DOUBLE,
                            description TEXT,
                            particularites TEXT,
                            caracteres TEXT
);

CREATE TABLE IndividuMonstre (
                            id INTEGER PRIMARY KEY AUTO_INCREMENT,
                            nom VARCHAR(255),
                            niveau INTEGER,
                            attaque INTEGER,
                            defense INTEGER,
                            vitesse INTEGER,
                            attaqueSpe INTEGER,
                            defenseSpe INTEGER,
                            pvMax INTEGER,
                            potentiel DOUBLE,
                            exp DOUBLE,
                            pv INTEGER,
                            espece_id INT DEFAULT NULL REFERENCES EspeceMonstre(id)
                                ON DELETE SET NULL
                                ON UPDATE CASCADE,
                            entraineur_equipe_id INT DEFAULT NULL REFERENCES Entraineur (id)
                                ON DELETE SET NULL
                                ON UPDATE CASCADE,
                            entraineur_boite_id INT DEFAULT NULL REFERENCES Entraineur (id)
                                ON DELETE SET NULL
                                ON UPDATE CASCADE

);

CREATE TABLE Zone ( id INTEGER PRIMARY KEY AUTO_INCREMENT,
                    nom VARCHAR(255),
                    expZone INTEGER,
                    fk_zone_Suivante INT DEFAULT NULL REFERENCES Zone(id)
                        ON DELETE SET NULL
                        ON UPDATE CASCADE,
                    fk_zone_Precedente INT DEFAULT NULL REFERENCES Zone(id)
                        ON DELETE SET NULL
                        ON UPDATE CASCADE
);

CREATE TABLE Zone_EspeceMonstre ( id_Zone INTEGER,
                                  id_EspeceMonstre INTEGER,
                                PRIMARY KEY (id_Zone,id_EspeceMonstre),
                                FOREIGN KEY (id_Zone) REFERENCES Zone(id),
                                FOREIGN KEY (id_EspeceMonstre) REFERENCES EspeceMonstre(id)
);


# CREATION DES ENTRAINEURS
INSERT INTO Entraineur (nom, argents) VALUES ('Bob',1500), ('Alice',1500), ('Clara',1500);

#CREATION DES ESPECES MONSTRES
INSERT INTO EspeceMonstre
(id, nom, type, baseAttaque, baseDefense, baseVitesse, baseAttaqueSpe, baseDefenseSpe, basePv,
 modAttaque, modDefense, modVitesse, modAttaqueSpe, modDefenseSpe, modPv,
 description, particularites, caracteres)
VALUES
    (1, 'springleaf', 'Graine', 9, 11, 10, 12, 14, 60,
     6.5, 9.0, 8.0, 7.0, 10.0, 14.0,
     'Un petit monstre espiègle au corps rond comme une graine. Il aime se cacher dans l’herbe haute et se dorer au soleil.',
     'Sa feuille sur la tête s’incline pour indiquer son humeur.',
     'Curieux, amical, un peu timide.'),

    (4, 'flamkip', 'Animal', 12, 8, 13, 16, 7, 50,
     10.0, 5.5, 9.5, 9.5, 6.5, 12.0,
     'Ce petit animal est toujours entouré d’une flamme dansante. Il déteste le froid et s’énerve facilement quand on tente d’éteindre son feu.',
     'Sa flamme change d’intensité selon son niveau d’énergie.',
     'Impulsif, joueur, loyal.'),

    (7, 'aquamy', 'Meteo', 10, 11, 9, 14, 14, 55,
     9.0, 10.0, 7.5, 12.0, 12.0, 13.5,
     'Une créature vaporeuse qui ressemble à un petit nuage. Les gouttes qui tombent de son corps sont pures et rafraîchissantes.',
     'Fait légèrement baisser la température autour de lui quand il s’endort.',
     'Calme, rêveur, mystérieux.'),

    (8, 'laoumi', 'Animal', 11, 10, 9, 8, 11, 58,
     11.0, 8.0, 7.0, 6.0, 11.5, 14.0,
     'Un petit ourson au pelage soyeux. Il adore se tenir debout et brandir ses petites pattes comme s’il dansait.',
     'Son grognement est plus mignon qu’effrayant, mais il devient redoutable pour défendre ses amis.',
     'Affectueux, protecteur, gourmand.'),

    (10, 'bugsyface', 'Insecte', 10, 13, 8, 7, 13, 45,
     7.0, 11.0, 6.5, 8.0, 11.5, 10.0,
     'Un insecte à la carapace luisante qui se déplace par petits bonds. Il communique en faisant vibrer ses antennes.',
     'Sa carapace devient plus dure après chaque mue, augmentant sa défense.',
     'Travailleur, sociable, infatigable.'),

    (13, 'galum', 'Mineral', 12, 15, 6, 8, 12, 55,
     9.0, 13.0, 4.0, 6.5, 10.5, 13.0,
     'Un golem ancien sculpté dans la pierre. Ses yeux s’illuminent d’une lueur mystérieuse quand il se met en garde.',
     'Peut rester immobile pendant des heures, le faisant passer pour une statue.',
     'Sérieux, stoïque, fiable.');

# CREATION DES INDIVIDUS MONSTRES
INSERT INTO IndividuMonstre (nom, niveau, attaque, defense, vitesse, attaqueSpe, defenseSpe, pvMax, potentiel,
                            exp, pv, espece_id, entraineur_equipe_id, entraineur_boite_id) VALUES

('springleaf',5,8,37,50,36,4,3000,5.0,4.0,7,1,2,2),
('Aquamy',4,3,63,10,14,65,4000,7.1,4.30,10,7,1,1),
('Bugsyface',1,15,27,20,22,36,1500,6.0,4.2,15,10,1,1),
('Galum',7,16,14,22,11,17,4100,5.0,18.1,12.1,13,3,3),
('Flamkip',9,11,23,29,17,10,7200,7.8,18.9,15.1,4,3,3);
