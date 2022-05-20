package com.cuupa.opengarden.pojos.field


enum class FieldType(val value: Int) {

    UNDEFINED(0),
    GREENHOUSE(1),
    FIELD(2),
    GROW_BED_OPEN(3),
    GROW_BED_ROOFED(4);

    companion object {
        fun fromValue(value: Int) = when (value) {
            0 -> UNDEFINED
            1 -> GREENHOUSE
            2 -> FIELD
            3 -> GROW_BED_OPEN
            4 -> GROW_BED_ROOFED
            else -> throw IllegalArgumentException("Unknown FieldType.VALUE for $value")
        }
    }
}
