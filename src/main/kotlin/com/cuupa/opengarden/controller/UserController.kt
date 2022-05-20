package com.cuupa.opengarden.controller

import com.cuupa.opengarden.configuration.UserDatabase
import com.cuupa.opengarden.displayobjects.RegisterUserDO
import com.cuupa.opengarden.persistence.user.UserEntity
import com.cuupa.opengarden.pojos.users.Roles
import com.cuupa.opengarden.services.I18NService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
@Controller
class UserController(
    private val userDatabase: UserDatabase,
    private val i18NService: I18NService,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    @GetMapping("/login")
    fun login() = ModelAndView("login")

    @GetMapping("/register")
    fun register() = ModelAndView("register").apply { addObject("new_user", RegisterUserDO()) }

    @PostMapping("/register")
    fun doRegister(@ModelAttribute user: RegisterUserDO): ModelAndView {

        val mov = ModelAndView("register")
        if (userDatabase.userExists(user.username)) {
            mov.addObject("success", false)
            mov.addObject("message", i18NService.getMessage(userAlreadyExists))
            return mov
        }

        if (user.password.isNullOrEmpty()) {
            mov.addObject("success", false)
            mov.addObject("message", i18NService.getMessage(passwordEmpty))
            return mov
        }

        if (user.password != user.passwordConfirmation) {
            mov.addObject("success", false)
            mov.addObject("message", i18NService.getMessage(passwordConfirmationFailed))
            return mov
        }

        val entity = UserEntity().apply {
            username = user.username!!
            password = passwordEncoder.encode(user.password)
            role = Roles.USER.name
        }
        userDatabase.save(entity)
        return ModelAndView("register").apply { addObject("success", true) }
    }

    companion object {
        private val userAlreadyExists = "userAlreadyExists"
        private val passwordEmpty = "passwordEmpty"
        private val passwordConfirmationFailed = "passwordConfirmationFailed"
    }
}