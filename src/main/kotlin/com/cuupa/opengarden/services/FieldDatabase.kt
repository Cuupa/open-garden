package com.cuupa.opengarden.services

import com.cuupa.opengarden.persistence.field.FieldRepository
import com.cuupa.opengarden.pojos.Field

class FieldDatabase(private val fieldRepository: FieldRepository?) {

    fun find(name: String?): List<Field> {
        name?.let {
            return fieldRepository?.findByUserId(name)?.map { Field(it) } ?: listOf()
        }

        return listOf()
    }
}
