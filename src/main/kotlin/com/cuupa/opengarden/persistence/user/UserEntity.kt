package com.cuupa.opengarden.persistence.user

import javax.persistence.*

@Entity
@Table(name = "users")
open class UserEntity {

    @Id
    open var username: String = ""

    open var password: String = ""

    open var enabled: Boolean = false

    open var role: String = ""

}