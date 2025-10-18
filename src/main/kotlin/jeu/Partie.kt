package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.especeAquamy
import org.example.especeFlamkip
import org.example.especeSpringleaf
import org.example.monde.Zone
import org.example.monstre.IndividuMonstre

/**
 * Cette classe permet de démarrer une partie.
 *
 * @property id de la partie
 * @property joueur qui démarre la partie
 * @property zone dans laquelle on démarre la partie
 */

class Partie (
    var id : Int,
    var joueur : Entraineur,
    var zone : Zone
) {

    /**
     * Le but de la méthode est de créer et proposer 3 individus au joueur qui doit en choisir un.
     *
     * On ajoute le monstre choisi à l'équipe de monstre du joueur et on renseigne que le monstre appartient bien au joueur.
     *
     * @property starter Monstre avec lequel on va démarrer l'aventure !
     *
     */

    fun choixStarter() {
        var starter : IndividuMonstre? = null
        val monstre1 = IndividuMonstre(1,"Springleaf", especeSpringleaf,null,1500.0) // choix des monstres
        val monstre2 = IndividuMonstre(2,"Flampkip", especeFlamkip,null,1500.0)
        val monstre3 = IndividuMonstre(3,"Aquamy", especeAquamy,null,1500.0)

        monstre1.afficheDetail() //Springleaf
        monstre2.afficheDetail() //Flampkip
        monstre3.afficheDetail() //Aquamy

        println("Choisissez un monstre parmis les 3 : \n" +
                "1 : Springleaf \n" +
                "2: Flampkip \n" +
                "3: Aquamy")

        var choixSelection = readln().toInt()

        while (choixSelection !in 1..3) {
            choixStarter()
        }

        if (choixSelection == 1) {
            starter = monstre1
        } else if (choixSelection == 2) {
            starter = monstre2
        } else {
            starter = monstre3
        }

        starter.renommer()
        joueur.equipeMonstre.add(starter)
        starter.entraineur  = joueur

    }

    /**
     * Cette méthode permet de permuter la place des monstres dans l'équipe.
     *
     * @property posInit On souhaite avoir la position
     * @property postNext On souhaite avoir la nouvelle position.
     * @property monstrePosInit Position actuelle du monstre
     * @property monstrePosNext Future position du monstre dans l'équipe
     *
     */

    fun modifierOrdreEquipe() {
        if (joueur.equipeMonstre.isNotEmpty()) { // Si la liste (qui représente l'équpe du joueur) n'est pas vide
            println("Quelle est la position initiale du monstre dans la tcheam ? ")
            var posInit = readln().toInt()

            println("Quelle est la nouvelle position du monstre ? : ")
            var posNext = readln().toInt()

            var monstrePosInit = joueur.equipeMonstre[posInit]
            var monstrePosNext = joueur.equipeMonstre[posNext]

            joueur.equipeMonstre[posInit] = monstrePosNext
            joueur.equipeMonstre[posNext] = monstrePosInit
            println(joueur.equipeMonstre)
        }
    }

    /**
     * Cette méthode permet simplement d’afficher les informations des monstres qui composent
     * l'équipe du joueur et demande au joueur s'il souhaite modifier l’ordre des monstres.
     *
     * @property actions Permet de demander l'action à réaliser (on attend une lettre minuscule en réponse)
     * @property actionsNumerique Permet de demander l'action à réaliser (on attend un chiffre compris dans une plage précise)
     */
    fun examineEquipe () {
        println("Voici la team :")
        for ((position, laBete) in joueur.equipeMonstre.withIndex()) {
            println("Le monstre : ${laBete.nom} est à la position n° $position")

        }

        while (true) {
            println("Vous pouvez réaliser plusieurs actions : ")
            println("Soit saisir la position du monstre pour avoir des détails supplémentaires. n\"")
            println("Soit saisir 'q' pour quitter ou 'm' pour modifier l'ordre de l'équipe.")

            var actions = readln().lowercase() // pour demander les caractères exclusivement en minuscule

            if (actions == "m") {
                modifierOrdreEquipe() // appel de la fonction qui permet de changer l'ordre des monstres
            } else if (actions == "q") {
                println("Vous allez repartir au menu principal !")
                return
            } else {
                var actionsNumerique = actions.toInt() // pour demander la position
                if (actionsNumerique in 0 until joueur.equipeMonstre.size) {
                    joueur.equipeMonstre[actionsNumerique].afficheDetail()
                } else {
                    println("Saisie invalide !")
                }

            }
        }
    }
    /**
     * Cette méthode indique au joueur la zone ou il se trouve et lui indique les actions possibles.
     * - 1 : Rencontre un monstre sauvage.
     * - 2 : Examine l'équipe de monstres.
     * - 3 : Aller à la zone suivante (en vérifiant qu'elle n'est pas nulle).
     * - 4 : Pareil que pour la 3.
     *
     */

    fun jouer () {
        while(true) {
            // Details sur la zone actuelle.
            println("Vous êtes à : '${zone.nom}', bienvenu ! ")
            println("Expérience de la zone : ${zone.expZone} xpies")
            println("Voici à qui vous aurez affaire : ${zone.especesMonstres.joinToString { it.nom }}")

            // Details des actions que le joueur peut réaliser.
            println("Voici ce que vous pouvez faire, cliquez : ")
            println("1 : Rencontrer un monstre sauvage.")
            println("2 : Examiner l'équipe.")
            println("3 : Aller à la zone suivante.")
            println("4 : Aller à la zone précédente.")

            // Chaque chiffre réalise une action choisie par le joueur.
            var indication = readln().toInt() // Lit l'action du joueur dans la console.
            when (indication) {
                1 -> zone.rencontreMonstre()
                2 -> examineEquipe()
                3 -> if (zone.zoneSuivante != null) {
                    zone = zone.zoneSuivante!!
                    println("Vous voici à ✴\uFE0E ${zone.nom} ✴\uFE0E.")
                } else {
                    println("Vous avez atteint la dernière zone.")
                }

                4 -> if (zone.zonePrecedente != null) {
                    zone = zone.zonePrecedente!!
                    println("Vous voici à ✴\uFE0E ${zone.nom} ✴\uFE0E.")
                } else {
                    println("Vous vous situez actuellement dans la première zone.")
                }
                else -> { println("Saisie invalide")

                }
            }
        }
    }

}