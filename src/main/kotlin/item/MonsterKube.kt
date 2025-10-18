package org.example.item
import org.example.joueur
import org.example.monstre.IndividuMonstre
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Représente un objet permettant de capturer et de stocker des monstres sauvages.
 *
 * On hérite de la classe mère [Item] pour récupérer les paramètres.
 *
 * MonsterKube comme pour le badge est un sous-type d'Item.
 *
 * @param id identifiant unique du monsterKube
 * @param nom du monsterKube
 * @param description du monsterKube
 * @property chanceCapture détermine la chance de pouvoir capturer un Monstre.
 */

class MonsterKube(  id: Int,
                    nom: String,
                    description: String,
                var chanceCapture: Double

    ): Item(id, nom, description), Utilisable {

    /**
     * Cette méthode implémente l'interface [Utilisable].
     * Donc, on doit définir la méthode [utiliser] pour appliquer son effet.
     *
     * Cette méthode permet d'effectuer différentes actions lorsqu'on est en possession du monsterKube.
     */

    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("Vous lancez le Monster Kube ! ")
        if (cible.entraineur != null) {
            println("Le Monstre ne peut pas être capturé. ")
            return false
        } else {
            var nbAleatoire = Random.nextInt(0..100)
            if (nbAleatoire < chanceCapture) {
                println("Le monstre est capturé ! ")
                println("Veuillez insérer un nouveau nom : ")
                var nouveauNom =  readLine()

                if (nouveauNom != null) {
                    cible.nom = nouveauNom
                }
                if (joueur.equipeMonstre.size >= 6) {
                    joueur.boiteMonstre.add(cible)
                } else {
                    joueur.equipeMonstre.add(cible)
                }
                cible.entraineur = joueur
                return true
            } else {
                println("Presque ! Le Kube n'a pas pu capturer le monstre ! ")
                return false
            }
        }

    }

}