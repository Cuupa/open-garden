package com.cuupa.opengarden.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class UserController {

    @GetMapping("/login")
    fun login() = ModelAndView("login")
}