package org.example.DAO

import org.example.db
import org.example.jdbc.BDD
import org.example.monstre.EspeceMonstre
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Statement


/**
 * DAO (Data Access Object) permettant d'interagir avec la table `EspeceMonstre`.
 *
 * Cette classe gère les opérations CRUD :
 * - 🔍 Lecture (findAll, findById, findByNom)
 * - 💾 Sauvegarde (save, saveAll)
 * - ❌ Suppression (deleteById)
 *
 * @param bdd L'objet de connexion à la base de données.
 */

class EspeceMonstreDAO (val bdd: BDD = db) {
    /**
     * Récupère toutes les Espèces de monstre présents dans la base de données.
     *
     * @return Une liste mutable [EspeceMonstre] trouvés.
     */
    fun findAll(): MutableList<EspeceMonstre> {
        val result = mutableListOf<EspeceMonstre>()
        val sql = "SELECT * FROM EspeceMonstre"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val type = resultatRequete.getString("type")
                val baseAttaque = resultatRequete.getInt("baseAttaque")
                val baseDefense = resultatRequete.getInt("baseDefense")
                val baseVitesse = resultatRequete.getInt("baseVitesse")
                val baseAttaqueSpe = resultatRequete.getInt("baseAttaqueSpe")
                val baseDefenseSpe = resultatRequete.getInt("baseDefenseSpe")
                val basePv = resultatRequete.getInt("basePv")
                val modAttaque = resultatRequete.getDouble("modAttaque")
                val modDefense = resultatRequete.getDouble("modDefense")
                val modVitesse = resultatRequete.getDouble("modVitesse")
                val modAttaqueSpe = resultatRequete.getDouble("modAttaqueSpe")
                val modDefenseSpe = resultatRequete.getDouble("modDefenseSpe")
                val modPv = resultatRequete.getDouble("modPv")
                val description = resultatRequete.getString("description")
                val particularites = resultatRequete.getString("particularites")
                val caracteres = resultatRequete.getString("caracteres")

                result.add(EspeceMonstre(id, nom,type, baseAttaque, baseDefense, baseVitesse, baseAttaqueSpe, baseDefenseSpe, basePv, modAttaque, modDefense, modVitesse, modAttaqueSpe, modDefenseSpe, modPv, description, particularites, caracteres))
            }
        }

        requetePreparer.close()
        return result
    }

    /**
     * Recherche un [EspeceMonstre] par son identifiant unique.
     *
     * @param id L'identifiant de l'[EspeceMonstre].
     * @return L'[EspeceMonstre] est trouvé ou `null` si aucun résultat.
     */

    fun findById(id: Int): EspeceMonstre? {
        var result: EspeceMonstre? = null
        val sql = "SELECT * FROM EspeceMonstre WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, id) // insere la valeur de l'id dans la requete preparer
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null && resultatRequete.next()) {
            val nom = resultatRequete.getString("nom")
            val type = resultatRequete.getString("type")
            val baseAttaque = resultatRequete.getInt("baseAttaque")
            val baseDefense = resultatRequete.getInt("baseDefense")
            val baseVitesse = resultatRequete.getInt("baseVitesse")
            val baseAttaqueSpe = resultatRequete.getInt("baseAttaqueSpe")
            val baseDefenseSpe = resultatRequete.getInt("baseDefenseSpe")
            val basePv = resultatRequete.getInt("basePv")
            val modAttaque = resultatRequete.getDouble("modAttaque")
            val modDefense = resultatRequete.getDouble("modDefense")
            val modVitesse = resultatRequete.getDouble("modVitesse")
            val modAttaqueSpe = resultatRequete.getDouble("modAttaqueSpe")
            val modDefenseSpe = resultatRequete.getDouble("modDefenseSpe")
            val modPv = resultatRequete.getDouble("modPv")
            val description = resultatRequete.getString("description")
            val particularites = resultatRequete.getString("particularites")
            val caracteres = resultatRequete.getString("caracteres")

            result = EspeceMonstre(id,nom,type, baseAttaque, baseDefense, baseVitesse, baseAttaqueSpe, baseDefenseSpe, basePv, modAttaque, modDefense, modVitesse, modAttaqueSpe, modDefenseSpe, modPv, description, particularites, caracteres)
        }

        requetePreparer.close()
        return result
    }

    /**
     * Recherche un [EspeceMonstre] par son nom.
     *
     * @param nomRechercher Le nom de l'[EspeceMonstre] à rechercher.
     * @return Une liste d'[EspeceMonstre] correspondant au nom donné.
     */

    fun findByNom(nomRechercher: String): MutableList<EspeceMonstre> {
        val result = mutableListOf<EspeceMonstre>()
        val sql = "SELECT * FROM EspeceMonstre WHERE nom = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setString(1, nomRechercher)
        val resultatRequete = bdd.executePreparedStatement(requetePreparer)

        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                val id = resultatRequete.getInt("id")
                val nom = resultatRequete.getString("nom")
                val type = resultatRequete.getString("type")
                val baseAttaque = resultatRequete.getInt("baseAttaque")
                val baseDefense = resultatRequete.getInt("baseDefense")
                val baseVitesse = resultatRequete.getInt("baseVitesse")
                val baseAttaqueSpe = resultatRequete.getInt("baseAttaqueSpe")
                val baseDefenseSpe = resultatRequete.getInt("baseDefenseSpe")
                val basePv = resultatRequete.getInt("basePv")

                val modAttaque = resultatRequete.getDouble("modAttaque")
                val modDefense = resultatRequete.getDouble("modDefense")
                val modVitesse = resultatRequete.getDouble("modVitesse")
                val modAttaqueSpe = resultatRequete.getDouble("modAttaqueSpe")
                val modDefenseSpe = resultatRequete.getDouble("modDefenseSpe")
                val modPv = resultatRequete.getDouble("modPv")

                val description = resultatRequete.getString("description")
                val particularites = resultatRequete.getString("particularites")
                val caracteres = resultatRequete.getString("caracteres")

                result.add(EspeceMonstre(id, nom, type, baseAttaque, baseDefense, baseVitesse,baseAttaqueSpe, baseDefenseSpe, basePv, modAttaque, modDefense, modVitesse, modAttaqueSpe, modDefenseSpe, modPv, description, particularites, caracteres))
            }
        }

        requetePreparer.close()
        return result
    }

    /**
     * Insère ou met à jour un [EspeceMonstre] dans la base.
     *
     * @param espece L'[EspeceMonstre] à sauvegarder.
     * @return L'[EspeceMonstre] est sauvegardé avec son ID et mis à jour si insertion.
     */

    fun save(espece: EspeceMonstre): EspeceMonstre? {
        val requetePreparer: PreparedStatement
        if (espece.id == 0) {
            val sql = "INSERT INTO EspeceMonstre (nom, type, baseAttaque, baseDefense, baseVitesse, baseAttaqueSpe, baseDefenseSpe, basePv, modAttaque, modDefense, modVitesse, modAttaqueSpe, modDefenseSpe, modPv, description, particularites, caracteres) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            requetePreparer.setString(1, espece.nom)
            requetePreparer.setString(2, espece.type)
            requetePreparer.setInt(3, espece.baseAttaque)
            requetePreparer.setInt(4, espece.baseDefense)
            requetePreparer.setInt(5, espece.baseVitesse)
            requetePreparer.setInt(6, espece.baseAttaqueSpe)
            requetePreparer.setInt(7, espece.baseDefenseSpe)
            requetePreparer.setInt(8, espece.basePv)
            requetePreparer.setDouble(9, espece.modAttaque)
            requetePreparer.setDouble(10, espece.modDefense)
            requetePreparer.setDouble(11, espece.modVitesse)
            requetePreparer.setDouble(12, espece.modAttaqueSpe)
            requetePreparer.setDouble(13, espece.modDefenseSpe)
            requetePreparer.setDouble(14, espece.modPv)
            requetePreparer.setString(15, espece.description)
            requetePreparer.setString(16, espece.particularites)
            requetePreparer.setString(17, espece.caracteres)
        } else {
            val sql = "UPDATE EspeceMonstre SET nom = ?, type = ?, baseAttaque = ?, baseDefense = ?, baseVitesse = ?, baseAttaqueSpe = ?, baseDefenseSpe = ?, basePv = ?, modAttaque = ?, modDefense = ?, modVitesse = ?, modAttaqueSpe = ?, modDefenseSpe = ?, modPv = ?, description = ?, particularites = ?, caracteres = ? WHERE id = ?"
            requetePreparer = bdd.connectionBDD!!.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            requetePreparer.setString(1, espece.nom)
            requetePreparer.setString(2, espece.type)
            requetePreparer.setInt(3, espece.baseAttaque)
            requetePreparer.setInt(4, espece.baseDefense)
            requetePreparer.setInt(5, espece.baseVitesse)
            requetePreparer.setInt(6, espece.baseAttaqueSpe)
            requetePreparer.setInt(7, espece.baseDefenseSpe)
            requetePreparer.setInt(8, espece.basePv)
            requetePreparer.setDouble(9, espece.modAttaque)
            requetePreparer.setDouble(10, espece.modDefense)
            requetePreparer.setDouble(11, espece.modVitesse)
            requetePreparer.setDouble(12, espece.modAttaqueSpe)
            requetePreparer.setDouble(13, espece.modDefenseSpe)
            requetePreparer.setDouble(14, espece.modPv)
            requetePreparer.setString(15, espece.description)
            requetePreparer.setString(16, espece.particularites)
            requetePreparer.setString(17, espece.caracteres)
            requetePreparer.setInt(18, espece.id)
        }

        val nbLigneMaj = requetePreparer.executeUpdate()
        if (nbLigneMaj > 0) {
            val generatedKeys = requetePreparer.generatedKeys
            if (generatedKeys.next()) {
                espece.id = generatedKeys.getInt(1)
            }
            requetePreparer.close()
            return espece
        }

        requetePreparer.close()
        return null
    }

    /**
    * Supprime l'[EspeceMonstre] par son identifiant.
    *
    * @param id L'ID de l'[EspeceMonstre] à supprimer.
    * @return `true` si la suppression a réussi, sinon `false`.
    */

    fun deleteById(id: Int): Boolean {
        val sql = "DELETE FROM EspeceMonstre WHERE id = ?"
        val requetePreparer = bdd.connectionBDD!!.prepareStatement(sql)
        requetePreparer.setInt(1, id)

        return try {
            val nbLigneMaj = requetePreparer.executeUpdate()
            requetePreparer.close()
            nbLigneMaj > 0
        } catch (erreur: SQLException) {
            println("Erreur lors de la suppression de l'espece monstre : ${erreur.message} !")
            false
        }
    }

    /**
     * Sauvegarde plusieurs [EspeceMonstre] dans la base de données.
     *
     * @param especes Liste d'especes à sauvegarder.
     * @return Liste des especes sauvegardées.
     */
    fun saveAll(especes: Collection<EspeceMonstre>): MutableList<EspeceMonstre> {
        val result = mutableListOf<EspeceMonstre>()
        for (esp in especes) {
            val sauvegarde = save(esp)
            if (sauvegarde != null) result.add(sauvegarde)
        }
        return result
    }
}