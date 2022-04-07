package com.cuupa.opengarden.configuration

import com.cuupa.opengarden.persistence.user.UserEntity
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    var dataSource: DataSource? = null

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/search").permitAll()
            .antMatchers("/crop").permitAll()
            .antMatchers("/crop/**").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/fonts/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/images/**").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/fields").permitAll()//.hasAnyRole("USER", "ADMIN")
            .antMatchers("/fields/**").permitAll()//.hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            //.failureHandler(authenticationFailureHandler())
            .failureUrl("/login?error")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
            .exceptionHandling()
        //.accessDeniedHandler(accessDeniedHandler())

        http.headers().frameOptions().sameOrigin()
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        dataSource?.let {
            auth.jdbcAuthentication()
                .dataSource(it)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(authoritiesQuery)
        }
    }

    @PostConstruct
    fun configLoaded() {
        log.info("Loaded ${SecurityConfiguration::class.simpleName}")
    }

    companion object {
        private val log = LogFactory.getLog(SecurityConfiguration::class.java)

        private val userQuery =
            "select username,password,enabled from ${UserEntity::class.java.simpleName} where username = ?"

        private const val authoritiesQuery = "select username,authority from authorities where username = ?"
    }
}
