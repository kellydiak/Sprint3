package org.example.monstre

import java.io.File

/**
 * Cette classe représente les caractéristiques de base d'un type de monstre. On possède différentes infos
 * le concernant comme son nom, id, ..
 *
 * @property id L'identifiant unique du monstre.
 * @property nom Le nom du monstre.
 * @property type Le type du monstre
 * @property baseAttaque
 * @property baseDefense
 * @property baseVitesse
 * @property baseAttaqueSpe
 * @property baseDefenseSpe
 * @property basePv
 * @property modAttaque Modificateur de croissance : attaque
 * @property modDefense Modificateur de croissance : défense
 * @property modVitesse Modificateur de croissance : vitesse
 * @property modAttaqueSpe Modificateur de croissance spécial : attaque
 * @property modDefenseSpe Modificateur de croissance spécial : défense
 * @property modPv Modificateur du point de vie
 * @property description Description du monstre
 * @property particularites Elements qui le différencie des autres
 * @property caracteres Détails sur le caractère (en 3 mots) du monstre

 */

class EspeceMonstre(
    var id: Int,
    var nom: String,
    var type : String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv:Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caracteres: String = ""
    ) {

    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     *
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */

    fun afficheArt(deFace: Boolean=true): String {
        val nomFichier = if(deFace) "front" else "back";
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "/")
        return safeArt.replace("\\u001B","\u001B")

    }


}