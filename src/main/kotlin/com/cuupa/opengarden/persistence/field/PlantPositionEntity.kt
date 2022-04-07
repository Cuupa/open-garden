package com.cuupa.opengarden.persistence.field

import javax.persistence.*

@Entity
@Table(name = "plant_position")
open class PlantPositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "plant")
    open var plantName: String = ""

    @Column(name = "positionX")
    open var positionX: Int = -1

    @Column(name = "positionY")
    open var positionY: Int = -1
}