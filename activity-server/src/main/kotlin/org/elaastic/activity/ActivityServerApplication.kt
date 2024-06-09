package org.elaastic.activity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ActivityServerApplication

fun main(args: Array<String>) {
	runApplication<ActivityServerApplication>(*args)
}
