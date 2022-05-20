package com.cuupa.opengarden.configuration

import com.cuupa.opengarden.persistence.user.UserRepository
import com.cuupa.opengarden.services.user.UserDetailServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class UserDBConfiguration {

    @Bean
    fun userDB(userRepository: UserRepository) = UserDatabase(userRepository)

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun userDetailService(repository: UserRepository) = UserDetailServiceImpl(repository)
}
