package com.cuupa.opengarden.pojos

import com.cuupa.opengarden.persistence.field.FieldEntity


data class Field(
    var name: String = "",
    var plantPosition: List<PlantPosition> = listOf(),
    var fieldType: FieldType = FieldType.UNDEFINED
) {

    constructor(entity: FieldEntity) : this(
        entity.name,
        entity.plantPosition.map { PlantPosition(it.plantName, it.positionX, it.positionY) },
        FieldType.fromValue(entity.fieldType.value)
    )
}