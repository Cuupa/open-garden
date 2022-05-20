package com.cuupa.opengarden.configuration

import com.cuupa.opengarden.persistence.user.UserEntity
import com.cuupa.opengarden.persistence.user.UserRepository

class UserDatabase(private val userRepository: UserRepository) {

    fun findUserByName(name: String) {
        userRepository.findByUsername(name)
    }

    fun userExists(name: String?) = name != null && userRepository.findByUsername(name) != null

    fun save(user: UserEntity) {
        userRepository.save(user)
    }

}
