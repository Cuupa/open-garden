package com.cuupa.opengarden.controller

import com.cuupa.opengarden.Search
import com.cuupa.opengarden.services.CookieService
import com.cuupa.opengarden.services.DOTranslateservice
import com.cuupa.opengarden.services.I18NService
import com.cuupa.opengarden.services.WeatherService
import com.cuupa.opengarden.services.geolocation.Coordinates
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
 * @author Simon Thiel (https://github.com/cuupa)
 */
@Controller
class UIController(
    private val i18n: I18NService,
    private val cookieService: CookieService,
    private val weatherService: WeatherService,
    private val doTranslateService: DOTranslateservice
) {

    @PostConstruct
    fun post() {
        log.info("Loaded UIController")
    }

    @GetMapping("/")
    fun index(session: HttpSession, req: HttpServletRequest): ModelAndView {
        val coordinates = cookieService.getCoordinatesFromCookies(req)
        val weather = weatherService.loadWeather(coordinates)

        return ModelAndView("index").apply {
            addObject("search", Search())
            addObject("text", i18n.getMessage("index-logo-text"))
            addObject("weather", doTranslateService.translate(weather))
        }
    }

    companion object {
        private val log = LogFactory.getLog(this.javaClass)
    }
}