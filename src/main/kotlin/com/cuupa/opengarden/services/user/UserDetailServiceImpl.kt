package com.cuupa.opengarden.services.user

import com.cuupa.opengarden.persistence.user.UserRepository
import com.cuupa.opengarden.pojos.user.AuthenticatedUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if(username.isNullOrBlank()){
            throw UsernameNotFoundException("")
        }
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("")
        return AuthenticatedUser(user)
    }


}
