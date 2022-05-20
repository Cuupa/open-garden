package com.cuupa.opengarden.persistence.user


import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthoritiesRepository : JpaRepository<AuthoritiesEntity, String> {

}

