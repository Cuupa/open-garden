package com.cuupa.opengarden.controller

import com.cuupa.opengarden.Search
import com.cuupa.opengarden.services.I18NService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView
import javax.annotation.PostConstruct

/**
 * @author Simon Thiel (https://github.com/cuupa)
 */
@Controller
class UIController(private val i18n: I18NService) {

    @PostConstruct
    fun post() {
        println("Loaded UIController")
    }

    @GetMapping("/")
    fun index(): ModelAndView {
        return ModelAndView("index").apply {
            addObject("search", Search())
            addObject("text", i18n.getMessage("index-logo-text"))
        }
    }
}