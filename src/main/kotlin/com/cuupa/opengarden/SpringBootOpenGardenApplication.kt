package com.cuupa.opengarden.com.cuupa.opengarden

import org.springframework.boot.SpringApplication.run
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author Simon Thiel (https://github.com/cuupa)
 */
@SpringBootApplication
open class SpringBootOpenGardenApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            run(SpringBootOpenGardenApplication::class.java, *args)
        }
    }
}