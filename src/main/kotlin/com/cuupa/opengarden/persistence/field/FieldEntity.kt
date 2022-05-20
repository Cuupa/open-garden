package com.cuupa.opengarden.persistence.field

import com.cuupa.opengarden.persistence.user.UserEntity
import javax.persistence.*

@Entity
@Table(name = "field")
open class FieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    open var id: Long? = null

    open var name = ""

    /**
     * UserId of the associated field
     */
    open var userId: String? = null

    @OneToMany(fetch = FetchType.LAZY)
    open var plantPosition = mutableListOf<PlantPositionEntity>()

    @Enumerated(EnumType.ORDINAL)
    open var fieldType: FieldTypeEntity = FieldTypeEntity.UNDEFINED

}