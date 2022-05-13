package com.cuupa.opengarden.controller

import com.cuupa.opengarden.configuration.UserDatabase
import com.cuupa.opengarden.displayobjects.RegisterUserDO
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class UserController(private val userDatabase: UserDatabase) {

    @GetMapping("/login")
    fun login() = ModelAndView("login")

    @GetMapping("/register")
    fun register() = ModelAndView("register").apply { addObject("new_user", RegisterUserDO()) }

    @PostMapping("/register")
    fun doRegister(@ModelAttribute user: RegisterUserDO): ModelAndView {
        var success = true

        if (userDatabase.userExists(user.username)) {
            success = false
        }

        if (user.password.isNullOrEmpty()) {
            success = false
        }

        if (user.password != user.passwordConfirmation) {
            success = false
        }

        if (success) {
            userDatabase.save(user)
        }
        return ModelAndView("register").apply { addObject("success", success) }
    }
}