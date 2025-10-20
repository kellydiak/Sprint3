package org.example

import org.example.DAO.EntraineurDAO
import org.example.DAO.EspeceMonstreDAO
import org.example.DAO.IndividuMonstreDAO
//import org.example.EspeceMonstreDAO
import org.example.dresseur.Entraineur
import org.example.item.Badge
import org.example.item.MonsterKube
import org.example.jdbc.BDD
import org.example.jeu.Partie
import org.example.monde.Zone
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre


/**
 * Change la couleur du message donné selon le nom de la couleur spécifié.
 * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console. Si un nom de couleur
 * non reconnu ou une chaîne vide est fourni, aucune couleur n'est appliquée.
 *
 * @param message Le message auquel la couleur sera appliquée.
 * @param couleur Le nom de la couleur à appliquer (ex: "rouge", "vert", "bleu"). Par défaut c'est une chaîne vide, ce qui n'applique aucune couleur.
 * @return Le message coloré sous forme de chaîne, ou le même message si aucune couleur n'est appliquée.
 */

fun changeCouleur(message: String, couleur: String=""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        "mauve" -> "\u001B[95m"
        "marron" -> "\u001B[38;5;137m"
        else -> "" //pas de couleur sir non reconnu
    }
    return "$codeCouleur$message$reset"
}

// Connexion à la base de données
val db = BDD()

//LES DAO
val entraineurDAO= EntraineurDAO(db)
val especeMonstreDAO = EspeceMonstreDAO(db)
val individuMonstreDAO = IndividuMonstreDAO(db)

// Les listes
val listeEntraineur = entraineurDAO.findAll()
val listeEspeceMonstre = especeMonstreDAO.findAll()
val listeIndividuMonstre = individuMonstreDAO.findAll()

//Objets Entraineur
var joueur = Entraineur(1,"Sacha",100)
var rival = Entraineur(2,"Regis",200)

//Objets EspeceMonstre

var especeSpringleaf = EspeceMonstre(1,"Springleaf","Graine",9,11,10,12,14,60,6.5,9.0,8.0,7.0,10.0,60.0,"Petit monstre espiègle rond comme une graine, adore le soleil.","Sa feuille sur la tête indique son humeur.","Curieux, amical, timide")
var especeFlamkip = EspeceMonstre(4,"Flamkip","Animal",12,8,13,16,7,50,10.0,5.5,9.5,9.5,6.5,50.0,"Petit animal entouré de flammes, déteste le froid.","Sa flamme change d’intensité selon son énergie.","Impulsif, joueur, loyal")
var especeAquamy = EspeceMonstre(7,"Aquamy","Meteo",10,11,9,14,14,55,9.0,10.0,7.5,12.0,12.0,55.0,"Créature vaporeuse semblable à un nuage, produit des gouttes pures.","Fait baisser la température en s’endormant.","Calme, rêveur, mystérieux")
var especeLaoumi = EspeceMonstre(8,"Laoumi","Animal",11,10,9,8,11,58,11.0,8.0,7.0,6.0,11.5,58.0,"Petit ourson au pelage soyeux, aime se tenir debout.","Son grognement est mignon mais il protège ses amis.","Affectueux, protecteur, gourmand")
var especeBugsyface = EspeceMonstre(10,"Bugsyface","Insecte",10,13,8,7,13,45,7.0,11.0,6.5,8.0,11.5,45.0,"Insecte à carapace luisante, se déplace par bonds et vibre des antennes.","Sa carapace devient plus dure après chaque mue.","Travailleur, sociable, infatigable")
var especeGalum = EspeceMonstre(13,"Galum","Minéral",12,15,6,8,12,55,9.0,13.0,4.0,6.5,10.5,55.0,"Golem ancien de pierre, yeux lumineux en garde.","Peut rester immobile des heures comme une statue.","Sérieux, stoïque, fiable")

// Espece Monstre edition special
var especeIceeFreezy = EspeceMonstre(14,"IceeFreezy","Animal",10,13,16,14,19,77,13.01,4.5,10.2,100.0,100.0,1.3,"Petit chat qui vient de la planète LuvOnFayar.","Lorsque on l'attaque il gèle et envoie de la neige à l'adversaire","Mignon, dalleux, pleurnichard")

//Objets Zone

var zone1 = Zone(1,"Périph'2Pvname",75, mutableListOf(especeSpringleaf, especeFlamkip),null,null)
var zone2 = Zone(2,"Savigny-le-Temple",77, mutableListOf(especeAquamy, especeLaoumi),null,null)

// Objets MonsterKube
var MK1 = MonsterKube(1,"MonsterKube1","Maison nomade carrée",50.0)
//var bagde1 = Badge(1,"Badge Roche","Badge gagné lorsque le joueur attaeint l'arène de pierre", joueur)

/**
 * Cette méthode :
 * - Affiche un message d’introduction.
 * - Demande et modifie le nom du joueur.
 * - Créer et retourne un nouvel objet 'Partie'.
 *
 * @property tonNom On instancie la variable qui va lire le nom du joueur.
 * @property partie1 On crée l'objet partie.
 * @return partie1 On renvoie ce dernier.
 **/

fun nouvellePartie(): Partie {
    println("Bonjour et bienvenu(e) dans la partie ! Mais avant tout, quel est votre nom ?")
    var tonNom = readln()
    joueur.nom = tonNom // On stocke le nom du joueur dans joueur.nom
    println("Merci, nous allons créer votre partie veuillez patienter ${joueur.nom} ..")
    var partie1 : Partie = Partie(1, joueur, zone1)
    joueur.id=0
    entraineurDAO.save(joueur) // ajout du joueur dans la BDD
    return partie1
}

fun main() {

    /* TEST INDIVIDUS MONSTRE 1*/
    val monstre1 = IndividuMonstre(1,"Springleaf", especeSpringleaf,null,1500.0)
    val monstre2 = IndividuMonstre(2,"Flampkip", especeFlamkip,null,1500.0)
    val monstre3 = IndividuMonstre(3,"Aquamy", especeAquamy,null,1500.0)



    zone1.zoneSuivante = zone2
    zone2.zonePrecedente = zone1
    joueur.sacAItems.add(MK1)

    // démarrage du jeu
    val partie = nouvellePartie()
    partie.choixStarter()
    db.close() //fermeture de la base de données
    partie.jouer()


    // AUTRE :
    //monstre1.attaquer(IndividuMonstre(2,"Flampkip", especeFlamkip,null,1500.0))
    //monstre1.renommer()
    //MK1.utiliser(monstre1)
    //monstre1.afficheDetail()

    /*TEST MONSTER KUBE
    println(MK1.utiliser(monstre1))*/
}
