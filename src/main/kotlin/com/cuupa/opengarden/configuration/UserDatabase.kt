package com.cuupa.opengarden.configuration

import com.cuupa.opengarden.displayobjects.RegisterUserDO
import com.cuupa.opengarden.persistence.user.UserEntity
import com.cuupa.opengarden.persistence.user.UserRepository

class UserDatabase(private val userRepository: UserRepository) {

    fun findUserByName(name: String) {
        userRepository.findByUsername(name)
    }

    fun userExists(name: String?) = userRepository.findByUsername(name) != null

    fun save(user: RegisterUserDO) {
        UserEntity().apply {
            username = user.username!!
            password = user.password!!
        }
    }

}
