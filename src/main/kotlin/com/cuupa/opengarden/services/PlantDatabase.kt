package com.cuupa.opengarden.services

import com.cuupa.opengarden.pojos.Plant
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.apache.commons.logging.LogFactory
import java.lang.reflect.Type
import java.nio.file.Files
import java.nio.file.Path
import java.util.*


class PlantDatabase {

    private val vegetables: List<Plant>
    private val gson = GsonBuilder().create()
    var type: Type = object : TypeToken<ArrayList<Plant>>() {}.type

    init {
        vegetables = initVegetables()
    }

    private fun initVegetables(): List<Plant> {
        val json = Files.readString(Path.of("data/vegetables.json"))
        return gson.fromJson(json, type)
    }

    fun find(searchTerm: String): Optional<Plant> {
        val result = vegetables.find {
            plantSearchPredicate(it, searchTerm.lowercase())
        }
        return if (result == null) {
            Optional.empty()
        } else {
            Optional.of(result)
        }
    }

    private fun plantSearchPredicate(it: Plant, searchTerm: String) =
        it.family?.lowercase() == searchTerm
                || it.genus?.lowercase() == searchTerm
                || it.bionomalName?.lowercase() == searchTerm
                || it.name?.lowercase() == searchTerm
                || it.order?.lowercase() == searchTerm
                || it.species?.lowercase() == searchTerm

    fun getRandomPlantName(): String {
        val randomPlant = vegetables.random()

        val value = when (val field = searchableFields.random()) {
            "name" -> randomPlant.name
            "bionomalName" -> randomPlant.bionomalName
            else -> {
                log.error("Can't query field '$field' for plant '$randomPlant' in PlantDatabase::getRandomPlantName()")
                ""
            }
        }
        return value ?: ""
    }

    companion object {
        val searchableFields = listOf("name", "bionomalName")
        private val log = LogFactory.getLog(PlantDatabase::class.java)
    }
}
