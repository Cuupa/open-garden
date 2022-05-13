package com.cuupa.opengarden.persistence.user


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, String> {

    fun findByUsername(username: String): UserEntity?
}

