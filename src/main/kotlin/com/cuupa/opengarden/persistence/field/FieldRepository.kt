package com.cuupa.opengarden.persistence.field

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FieldRepository : JpaRepository<FieldEntity, Long> {

    fun findByUserId(userId: String): List<FieldEntity>
}

