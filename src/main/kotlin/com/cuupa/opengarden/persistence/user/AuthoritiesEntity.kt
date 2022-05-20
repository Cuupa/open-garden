package com.cuupa.opengarden.persistence.user

import javax.persistence.*

@Entity
@Table(name = "authorities")
open class AuthoritiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    open var id: String = ""

    open var username: String = ""

    open var authority: String = ""

    open var enabled: Boolean = false
}