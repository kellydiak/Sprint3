package org.example.item
import org.example.dresseur.Entraineur


/**
 * Cette classe permet de créer un objet spécifique de la classe Item.
 *
 * Récompense donnée au joueur après avoir battu une arène. Sert à débloquer du contenu ou prouver la progression.
 *
 * @param id du badge
 * @param nom du badge
 * @param description permet de décrire le badge
 * @property champion représente l’entraîneur le plus fort ou l’adversaire final que le joueur doit affronter pour terminer la partie.
 * Afin de devenir champion à son tour.
 *
 */

class Badge (id: Int,
            nom: String,
            description: String,
            var champion: Entraineur

): Item (id,nom,description){

}