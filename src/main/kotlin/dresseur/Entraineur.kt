package org.example.dresseur

import org.example.item.Item
import org.example.monstre.IndividuMonstre

/**
 * Cette classe représente le personnage contrôlé par le joueur ou un PNJ. Equivalent d'un dresseur.
 *
 * Un entraîneur est responsable de gérer une équipe de monstres, une boîte pour stocker des monstres supplémentaires
 * et un sac contenant des objets appelés MonsterKubes. L'entraîneur a également une somme d'argent associée.
 *
 * @property id L'identifiant unique de l'entraîneur.
 * @property nom Le nom de l'entraîneur.
 * @property argents La quantité d'argent en possession de l'entraîneur.
 * @property equipeMonstre Equipe de monstre géré par l'entraîneur.
 * @property boiteMonstre Boite qui permet de stocker les futurs monstres.
 * @property sacAItems Permet de stocker les objets de l'entraineur.

 */

class Entraineur(var id: Int,
                 var nom: String,
                 var argents: Int,
    var equipeMonstre: MutableList<IndividuMonstre> = mutableListOf(),
    var boiteMonstre: MutableList<IndividuMonstre> = mutableListOf(),
    var sacAItems: MutableList<Item> = mutableListOf())
{

/**
 * Méthode qui affiche les détails de l'entraîneur : nom et argent possédé.
 */

    fun afficheDetail() {
        println("Dresseur : ${this.nom}")
        println("Argent : ${this.argents}")

    }

}
