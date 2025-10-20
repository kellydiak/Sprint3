package org.example.DAO

import org.example.db
import org.example.jdbc.BDD
import org.example.monstre.IndividuMonstre
import org.example.monstre.EspeceMonstre
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement


/**
 * DAO (Data Access Object) permettant d'interagir avec la table `IndividuMonstre`.
 *
 * Cette classe gère les opérations CRUD :
 * - 🔍 Lecture (findAll, findById, findByNom)
 * - 💾 Sauvegarde (save, saveAll)
 * - ❌ Suppression (deleteById)
 *
 * @param bdd L'objet de connexion à la base de données.
 */

class IndividuMonstreDAO (val bdd: BDD = db) {
    val entraineur_Dao = EntraineurDAO()
    val espece_Dao = EspeceMonstreDAO()

    /**
     * Récupère tous les [IndividuMonstre] présents dans la base de données.
     *
     * @return Une liste mutable des individus monstres trouvés.
     */
    fun findAll(): MutableList<IndividuMonstre> {
        val result = mutableListOf<IndividuMonstre>()
        val sql = "SELECT * FROM IndividuMonstre"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val espece_id = resultatRequete.getInt("espece_id")
                val entraineur_id = resultatRequete.getInt("entraineur_id")
                val expInit = resultatRequete.getDouble("exp_init")
                val espece = espece_Dao.findById(espece_id)!!
                val entraineur = entraineur_Dao.findById(entraineur_id)
                result.add(IndividuMonstre(id,nom,espece,entraineur,expInit))
            }
        }

        requetePreparer.close()
        return result
    }

    /**
     * Recherche un [IndividuMonstre] par son identifiant unique.
     *
     * @param id L'identifiant de l'individu monstre.
     * @return L'individu monstre trouvé ou `null` si aucun résultat.
     */
    fun findById(id: Int): IndividuMonstre? {
        var result: IndividuMonstre? = null
        val sql = "SELECT * FROM IndividuMonstre WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, id) // insere la valeur de l'id dans la requete preparer
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null && resultatRequete.next()) {
            val nom = resultatRequete.getString("nom")
            val espece_id = resultatRequete.getInt("espece_id")
            val entraineur_id = resultatRequete.getInt("entraineur_id")
            val expInit = resultatRequete.getDouble("exp_init")
            val espece = espece_Dao.findById(espece_id)!!
            val entraineur = entraineur_Dao.findById(entraineur_id)
            result = IndividuMonstre(id, nom, espece, entraineur, expInit)
        }
        requetePreparer.close()
        return result
    }

    /**
     * Recherche un IndividuMonstre par son nom.
     *
     * @param nomRechercher Le nom de l'individu à rechercher.
     * @return Une liste d'individus de monstres correspondant au nom donné.
     */
    fun findByNom(nomRechercher: String): MutableList<IndividuMonstre> {
        val result = mutableListOf<IndividuMonstre>()
        val sql = "SELECT * FROM IndividuMonstre WHERE nom = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setString(1, nomRechercher)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val espece_id = resultatRequete.getInt("espece_id")
                val entraineur_id = resultatRequete.getInt("entraineur_id")
                val expInit = resultatRequete.getDouble("exp_init")
                val espece = espece_Dao.findById(espece_id)!!
                val entraineur = entraineur_Dao.findById(entraineur_id)
                result.add(IndividuMonstre(id, nom, espece, entraineur, expInit))
            }
        }

        requetePreparer.close()
        return result
    }

    /**
     * Insère ou met à jour un individuMonstre dans la base.
     *
     * @param individu L'individu à sauvegarder.
     * @return L'individuMonstre sauvegardé avec son ID mis à jour si insertion.
     */
    fun save(individu: IndividuMonstre): IndividuMonstre? {
        val requetePreparer: PreparedStatement

        if (individu.id == 0) {
            // Insertion
            val sql = "INSERT INTO IndividuMonstre (nom,niveau,attaque,defense,vitesse,attaqueSpe,defenseSpe,pvMax,potentiel,exp,pv) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            requetePreparer.setString(1, individu.nom)
            requetePreparer.setInt(2, individu.niveau)
            requetePreparer.setInt(3, individu.attaque)
            requetePreparer.setInt(4, individu.defense)
            requetePreparer.setInt(5, individu.vitesse)
            requetePreparer.setInt(6, individu.attaqueSpe)
            requetePreparer.setInt(7, individu.defenseSpe)
            requetePreparer.setInt(8, individu.pvMax)
            requetePreparer.setDouble(9, individu.potentiel)
            requetePreparer.setDouble(10, individu.exp)
            requetePreparer.setInt(11, individu.pv)
        } else {
            // Mise à jour
            val sql = "UPDATE IndividuMonstre SET nom = ?, niveau = ?,attaque = ?, defense = ?,vitesse = ?,attaqueSpe = ?,defenseSpe = ?,pvMax = ?, potentiel = ?,exp = ?, pv = ? WHERE id = ?"
            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
            requetePreparer.setString(1, individu.nom)
            requetePreparer.setInt(2, individu.niveau)
            requetePreparer.setInt(3, individu.attaque)
            requetePreparer.setInt(4, individu.defense)
            requetePreparer.setInt(5, individu.vitesse)
            requetePreparer.setInt(6, individu.attaqueSpe)
            requetePreparer.setInt(7, individu.defenseSpe)
            requetePreparer.setInt(8, individu.pvMax)
            requetePreparer.setDouble(9, individu.potentiel)
            requetePreparer.setDouble(10, individu.exp)
            requetePreparer.setInt(11, individu.pv)
            requetePreparer.setInt(12, individu.id)
        }

        val nbLigneMaj = requetePreparer.executeUpdate()

        if (nbLigneMaj > 0) {
            val generatedKeys = requetePreparer.generatedKeys
            if (generatedKeys.next()) {
                individu.id = generatedKeys.getInt(1)
            }
            requetePreparer.close()
            return individu
        }

        requetePreparer.close()
        return null
    }

    /**
    * Supprime un [IndividuMonstre] par son identifiant.
    *
    * @param id L'ID de l'individu monstre à supprimer.
    * @return `true` si la suppression a réussi, sinon `false`.
    */
    fun deleteById(id: Int): Boolean {
        val sql = "DELETE FROM IndividuMonstre WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, id)

        return try {
            val nbLigneMaj = requetePreparer.executeUpdate()
            requetePreparer.close()
            nbLigneMaj > 0
        } catch (erreur: SQLException) {
            println("Erreur lors de la suppression de l'entraîneur : ${erreur.message}")
            false
        }
    }

    /**
     * Sauvegarde plusieurs individus dans la base de données.
     *
     * @param individus Liste d'individus à sauvegarder.
     * @return Liste des individus sauvegardés.
     */
    fun saveAll(individus: Collection<IndividuMonstre>): MutableList<IndividuMonstre> {
        val result = mutableListOf<IndividuMonstre>()
        for (indiv in individus) {
            val sauvegarde = save(indiv)
            if (sauvegarde != null) result.add(sauvegarde)
        }
        return result
    }







}