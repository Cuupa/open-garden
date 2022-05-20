package com.cuupa.opengarden.configuration

import com.cuupa.opengarden.services.user.UserDetailServiceImpl
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import javax.annotation.PostConstruct
import javax.sql.DataSource

@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    @Qualifier("user_datasource")
    var dataSource: DataSource? = null

    @Autowired
    var userDetailService: UserDetailServiceImpl? = null

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
            .antMatchers("/register/**").permitAll()
            .antMatchers("/fields/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .failureHandler(authenticationFailureHandler())
            .failureUrl("/login?error")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler())

        http.headers().frameOptions().sameOrigin()
    }

    @Override
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailService)
    }

    private fun accessDeniedHandler(): AccessDeniedHandler {
        return AccessDeniedHandler { request, response, exception ->
            println(exception)
            println(request)
            println(response)
        }
    }

    private fun authenticationFailureHandler(): AuthenticationFailureHandler {
        return AuthenticationFailureHandler { request, response, exception ->
            println(exception)
            println(request)
            println(response)
        }
    }

    @PostConstruct
    fun configLoaded() {
        log.info("Loaded ${SecurityConfiguration::class.simpleName}")
    }

    companion object {
        private val log = LogFactory.getLog(SecurityConfiguration::class.java)
    }
}
