package com.cuupa.opengarden.configuration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Simon Thiel (https://github.com/cuupa)
 */
@SpringBootApplication
class SpringBootOpenGardenApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<SpringBootOpenGardenApplication>(*args)
        }
    }
}