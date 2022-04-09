package com.cuupa.opengarden.pojos

import com.cuupa.opengarden.persistence.field.FieldEntity


data class Field(
    var name: String = "",
    var plantPosition: List<PlantPosition> = listOf(),
    var fieldType: FieldType = FieldType.UNDEFINED,
    var length: Int = -1,
    var width: Int = -1
) {

    constructor(entity: FieldEntity) : this(
        entity.name,
        entity.plantPosition.map { PlantPosition(it.plantName, it.positionX, it.positionY) },
        FieldType.fromValue(entity.fieldType.value)
    )
}