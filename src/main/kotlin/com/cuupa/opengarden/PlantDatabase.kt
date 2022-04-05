package com.cuupa.opengarden

import com.cuupa.opengarden.pojo.Plant
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.nio.file.Files
import java.nio.file.Path
import java.util.*


class PlantDatabase {
    private val vegetables = initVegetables()
    private val gson = GsonBuilder().create()
    var type = object : TypeToken<ArrayList<Plant>>() {}.type

    private fun initVegetables(): List<Plant> {
        val json = Files.readString(Path.of("data/vegetables"))
        return gson.fromJson(json, type)
    }

    fun find(searchTerm: String): Optional<Plant> {
        val result = vegetables.find {
            plantSearchPredicate(it, searchTerm)
        }
        return if (result == null) {
            Optional.empty()
        } else {
            Optional.of(result)
        }
    }

    private fun plantSearchPredicate(it: Plant, searchTerm: String) =
        it.family == searchTerm
                || it.genus == searchTerm
                || it.bionomalName == searchTerm
                || it.name == searchTerm
                || it.order == searchTerm
                || it.species == searchTerm
}
