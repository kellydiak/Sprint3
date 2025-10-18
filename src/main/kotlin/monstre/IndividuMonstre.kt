package org.example.monstre

import org.example.dresseur.Entraineur
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.pow
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.math.round

/**
 * Cette classe représente chaque individu, c'est-à-dire les monstres avec le joueur va interagir
 * (les monstres sauvages, les monstres de l’équipe du joueur, les monstres des autres dresseurs).
 *
 * Il y a un nombre conséquent de propriétés et de méthodes.
 *
 *
 * @constructor IndividuMonstre représente le constructeur primaire
 * @param expInit
 * @property id de l'individu monstre
 * @property nom de l'individu monstre
 * @property espece à laquelle est rattaché l'individu monstre
 * @property entraineur qui coach l'individu monstre
 * @property niveau représente le niveau actuel de l'individu monstre.
 * @property attaque représente la puissance de l'attaque du monstre.
 * @property defense représente la puissance de la défense du monstre.
 * @property vitesse représente la vitesse du monstre
 * @property attaqueSpe représente la puissance de l'attaque spéciale
 * @property defenseSpe représente la puissance de la défense spéciale
 * @property pvMax représente le point de vie maximal d'un individu monstre
 * @property potentiel Facteur multiplicatif qui influence la croissance des statistiques lors d’un level-up
 * @property exp Quantité d’expérience accumulée par un individu monstre
 * @property pv représente les points de vie actuels.
 * Ne peuvent pas être inférieurs à 0 ni supérieurs à pvMax
 */

class IndividuMonstre constructor(
    var id: Int,
    var nom: String,
    var espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    expInit: Double
) {
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + listOf<Int>(-2, 2).random()
    var defense: Int = espece.baseDefense + listOf<Int>(-2, 2).random()
    var vitesse: Int = espece.baseVitesse + listOf<Int>(-2, 2).random()
    var attaqueSpe: Int = espece.baseAttaqueSpe + listOf<Int>(-2, 2).random()
    var defenseSpe: Int = espece.baseDefenseSpe + listOf<Int>(-2, 2).random()
    var pvMax: Int = espece.basePv + listOf<Int>(-5, 5).random()
    var potentiel: Double = Random.nextDouble(0.5, 2.0)


    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value
            if (niveau == 1) {
                true
            } else {
                false
            }
            while (field >= palierExp(niveau)) {
                levelUp()
                if (niveau != 1) {
                    println("le monstre $nom est maintenant niveau $niveau !")
                }
            }

        }


    /**
     *  @property pv  Points de vie actuels.
     * Ne peut pas être inférieur à 0 n de i supérieur à [pvMax].
     */

    var pv: Int = 0
        get() = field
        set(nouveauPv) {
            if ((nouveauPv < 0)) {
                field = 0
            } else if (nouveauPv>pvMax){
                field=pvMax
            }
            else{
                field=nouveauPv
            }
        }

    init {
        this.exp = expInit
        this.pv=pvMax
        // Applique le setter et déclenche un éventuel level-up
    }


    /**
     * Cette méthode calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */

    fun palierExp(niveau: Int): Int {
        var expCumulee = 100 * (niveau - 1).toDouble().pow(2.0).toInt()
        return expCumulee
    }

    /**
     * Cette méthode augmente le niveau d'un monstre.
     * De plus elle recalcule les nouvelles valeurs des caractéristiques.
     *
     */

    fun levelUp() {
        niveau += 1

        attaque += round((espece.modAttaque * potentiel)).toInt() + listOf<Int>(-2, 2).random()
        defense += round((espece.modDefense * potentiel)).toInt() + listOf<Int>(-2, 2).random()
        vitesse += round((espece.modVitesse * potentiel)).toInt() + listOf<Int>(-2, 2).random()
        attaqueSpe += round((espece.modAttaqueSpe * potentiel)).toInt() + listOf<Int>(-2, 2).random()
        defenseSpe += round((espece.modDefense * potentiel)).toInt() + listOf<Int>(-2, 2).random()
        pvMax += round((espece.modPv * potentiel)).toInt() + Random.nextInt(-5, 5)

        pv += pvMax
    }

    /**
     * Cette méthode permet à un monstre d'attaquer une cible (un autre monstre).
     * Cela lui inflige des dégâts.
     *
     * Les dégâts sont calculés de manière très simple pour le moment :
     * dégâts = attaque - (défense/2). Avec pour minimum 1 dégât.
     *
     * @param cible Monstre cible de l'attaque.
     */

    fun attaquer(cible: IndividuMonstre) {
        var degatBrut = this.attaque
        var degatTotal = degatBrut - (this.defense / 2)

        if (degatTotal < 1) {
            degatTotal = 1
        }
        var pvAvant = cible.pv
        cible.pv -= degatTotal
        var pvApres = cible.pv
        println("$nom inflige ${pvAvant - pvApres} dégâts à ${cible.nom}")
    }

    // méthode qui permet de renommer le monstre
    fun renommer() {
        println("Voulez-vous renommer $nom ?")
        var nouveauNom: String = readLine().toString()
        if (nouveauNom != null) {
            this.nom = nouveauNom
            println("Voici son nouveau nom ${this.nom} !")
        } else {
            println("Vous vous appelez toujours $nom !")
        }
    }

    // affiche-les details du monstre
    fun afficheDetail() {
        println("================")
        println("nom : $nom")
        println("PV : $pv / $pvMax")
        println("niveau : $niveau")
        println("================")
        println("atq : $attaque")
        println("def : $defense")
        println("vitesse : $vitesse")
        println("attaqueSpe : $attaqueSpe")
        println("DefSpe : $defenseSpe")
        println(this.espece.afficheArt(true)) //donné par Issa pour faire appel à méthode afficheArt

    }


}




