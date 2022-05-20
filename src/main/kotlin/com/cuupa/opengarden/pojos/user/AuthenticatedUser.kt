package com.cuupa.opengarden.pojos.user

import com.cuupa.opengarden.persistence.user.UserEntity
import com.cuupa.opengarden.pojos.users.Roles
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthenticatedUser(user: UserEntity) : UserDetails {

    private val username: String
    override fun getUsername() = username

    private val password: String
    override fun getPassword() = password

    private val enabled: Boolean

    private val authorities: List<GrantedAuthority>
    override fun getAuthorities() = authorities

    init {
        username = user.username
        password = user.password
        enabled = user.enabled
        authorities = getRoles(user)
    }

    // TODO: empty roles allowed?
    private fun getRoles(user: UserEntity): List<SimpleGrantedAuthority> {
        return if (user.role.isBlank()
                .or(!user.role.contains(","))
        ) {
            listOf(SimpleGrantedAuthority(Roles.USER.name))
        } else {
            user.role.split(",").map { it.trim() }.map { SimpleGrantedAuthority(it) }
        }
    }

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = enabled
}