package org.example.jeu

import org.example.dresseur.Entraineur
import org.example.item.Item
import org.example.item.Utilisable
import org.example.joueur // faire appel à l'objet crée "joueur" permet l'accès aux propriétés de la classe Entraineur
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre


/**
 * Représente un combat entre l’équipe de monstre du joueur et un monstre sauvage.
 * Gère les tours, l’ordre d’action et l’affichage du combat.
 *
 * @property monstreJoueur représente l'individuMonstre du joueur
 * @property monstreSauvage représente un individuMonstre rebel (qui vit dans la nature)
 * @property round correspond au tour dans un combat et vaut 1 au départ.
 */


class CombatMonstre(var monstreJoueur: IndividuMonstre,
                    var monstreSauvage: IndividuMonstre
) {
    var round: Int = 1

    /**
     * Cette méthode permet de déterminer si le joueur a perdu ou pas.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */

    fun gameOver(): Boolean {
        var aPerdu = true
        for (monstre in joueur.equipeMonstre){
            if (monstre.pv > 0) aPerdu = false
        }
        return aPerdu
    }


    /**
     * Méthode qui indique si le joueur a gagné le combat.
     * Il y a deux façons de gagner le combat :
     * - Capturer le montre sauvage
     * - Ou amener les pvs du monstre sauvage à 0.
     * Le monstre du joueur gagne de l'expérience seulement dans le 2e cas.
     *
     * @return `true` si le joueur a gagné, sinon `false`.
     */

    fun joueurGagne () : Boolean {
        if (monstreSauvage.pv <= 0) { //if to while
            println("${joueur.nom} a gagné !")
            var gainExp = monstreSauvage.exp*0.20
            monstreSauvage.exp += gainExp
            println("${monstreJoueur.nom} gagne $gainExp xpies !")
            return true
        }
            if (monstreSauvage.entraineur == joueur) {
                println("${monstreSauvage.nom} a été capturé !")
                return true
            } else {
                return false
            }

    }

    /**
     * Cette méthode montre que si le pv du monstreSauvage est > 0 alors ce
     * dernier peut attaquer le monstreJoueur.
     *
     */

    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) {
            monstreSauvage.attaquer(monstreJoueur)
        }
    }

    /**
     * Cette méthode permet au joueur d’effectuer plusieurs actions. Retourne true si le combat doit continuer sinon false.
     * - Si le joueur tape 1 : le monstre du joueur attaque le monstre sauvage.
     * - Si le joueur tape 2 : on donne la possibilité au joueur d'utiliser un item
     * - Si le joueur tape 3 : le joueur peut changer son monstre actuel contre un autre monstre de son équipe
     */

    fun actionJoueur() : Boolean {
        if (gameOver() == true) {
            return false
        } else {
        println("Voici ce que vous pouvez faire : \n" +
                "Tapez 1 : Votre monstre attaque le monstre sauvage. \n" +
                "Tapez 2 : Utilisation d'un item. \n" +
                "Tapez 3 : Switcher de monstre.")
        var choixAction: Int = readln().toInt()

        if (choixAction == 1) {
            monstreJoueur.attaquer(monstreSauvage)

        } else if (choixAction == 2 ) {
            for ((position,ustensile) in joueur.sacAItems.withIndex()){
                println("$position : ${ustensile.nom}")
            }

            println("Choisissez un chiffre qui représente votre item : ")
            var indexChoix: Int = readln().toInt()
            var objetChoisi = joueur.sacAItems[indexChoix]
            if (objetChoisi is Utilisable) {
                var captureReussie = objetChoisi.utiliser(monstreSauvage)
                if (captureReussie) return false
            } else {
                println("Objet non utilisable !")
            }

        } else if (choixAction == 3) {
            for ((position,bestiole) in joueur.equipeMonstre.withIndex()){
                println("$position -> ${bestiole.nom}")
            }
            println("Choisissez un monstre dans l'équipe : ")
            var indexChoix: Int = readln().toInt()
            var choixMonstre = joueur.equipeMonstre[indexChoix]
            if (choixMonstre.pv <= 0) {
                println("Impossible ! Ce monstre est KO :( ")
            } else {
                println("${choixMonstre.nom} remplace ${monstreJoueur.nom}")
                monstreJoueur = choixMonstre
            }

        } else {
            print("chiffre invalide !")
        }
        }
        return true
    }

    /**
     * Cette méthode affiche les données du monstre du joueur et du monstre sauvage ainsi que leur ascii art.
     */

    fun afficheCombat() {
        println("======== Début Round : $round ========")
        println("Niveau : ${monstreSauvage.niveau}")
        println("PV : ${monstreSauvage.pv / monstreSauvage.pvMax}")
        println(monstreSauvage.espece.afficheArt())
        println(monstreJoueur.espece.afficheArt(false))
        println("Niveau : ${monstreJoueur.niveau}")
        println("PV : ${monstreJoueur.pv / monstreJoueur.pvMax}")
    }

    /**
     * Cette méthode fait jouer les deux monstres (honneur à celui dont la vitesse est élevée).
     * Si l'appel de this.actionJoueur() retourne false, on arrête l'exécution de la méthode jouer() avec un return vide.
     */

    fun jouer() {
        var joueurPlusRapide = monstreJoueur.vitesse >= monstreSauvage.vitesse
        afficheCombat()
        if (joueurPlusRapide ) {
            var continuer = this.actionJoueur()
            if (continuer == false) {
                return
            } else {
                actionAdversaire()
            }
        } else {
            actionAdversaire()
            if (gameOver() == false) {
                var continuer = this.actionJoueur()

                if (continuer == false ){
                    return
                }
            return
            }
        }
    }

    /**
     * Cette méthode lance le combat et gère les rounds jusqu'à la victoire ou la défaite.
     *
     * Affiche un message de fin si le joueur perd et restaure les PV de tous ses monstres.
     *
     */

    fun lancerCombat() {
        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }



}