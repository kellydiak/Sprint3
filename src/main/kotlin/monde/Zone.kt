package org.example.monde

import org.example.jeu.CombatMonstre
import org.example.joueur
import org.example.monstre.EspeceMonstre
import org.example.monstre.IndividuMonstre

/**
 * Cette classe représente un lieu où le joueur peut se déplacer et rencontrer des monstres.
 * À partir de là où on se trouve, on peut soit aller dans une zone antérieure (si possible) ou postérieure (si possible).
 *
 * @property id représente l'identification de la zone.
 * @property nom représente le nom de la zone.
 * @property expZone représente l'expérience de la zone.
 * @property especesMonstres est une liste mutable d'espèces de monstre qu'on peut rencontrer dans la zone. (avec une valeur par défaut = liste mutable vide)
 * @property zoneSuivante si on se trouve sur une zone x, c'est la zone x+1
 * @property zonePrecedente si on se trouve sur une zone x, c'est la zone x-1
 *
 */

class Zone (
    var id: Int,
    var nom: String,
    var expZone: Int,
    val especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {
    /**
     * Cette méthode permet de générer un individu monstre.
     *
     * @property especesRandom On génère d'abord l'espèce
     * @property expZoneRandom Permet de générer aléatoirement l'xp de la zone
     * @property monstreFinal On finalise la création de l'individu généré grace aux différentes données
     *
     */

    fun genereMonstre() : IndividuMonstre {
        var especesRandom = especesMonstres.random()
        var expZoneRandom : Double = expZone + listOf<Double>(-0.20,0.20).random() // obligée de passer en double pour utiliser le pourcentage
        var monstreFinal = IndividuMonstre(especesRandom.id,especesRandom.nom,especesRandom,null,expZoneRandom)
        return monstreFinal
    }

    /**
     * Cette méthode démarre un combat entre un monstre sauvage généré (grâce à la méthode précédente)
     * et le premier monstre de l’équipe du joueur qui a des pv >0.
     *
     * @property monstreSauvage Représente un monstre non capturé
     * @property premierMonstre Représente le premier monstre de l'équipe du joueur qui a des PVs > 0
     * @property fight Met en rivalité les deux monstres
     */

    fun rencontreMonstre () {
        var monstreSauvage = genereMonstre()
        var premierMonstre : IndividuMonstre? = null
        for (monstre in joueur.equipeMonstre) {
            if (monstre.pv > 0) premierMonstre = monstre
        }
        var fight = CombatMonstre(premierMonstre!!,monstreSauvage)
        fight.lancerCombat()
    }

}