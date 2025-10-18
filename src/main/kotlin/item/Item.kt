package org.example.item


/**
 * Classe principale dont d'autres classes vont hériter les propriétés.
 * Elle possède des propriétés de base de tous les sous-types d'Item.
 *
 * @property id de l'item
 * @property nom de l'item
 * @property description de l'item
 *
 */

open class Item (
    var id: Int,
    var nom: String,
    var description: String) {

}