package com.example.resource.sample.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sample")
class SampleController {

    @GetMapping("/", produces = ["application/json"])
    fun restricted(): Any {
        val result = HashMap<String, Any?>()
        result["message"] = "Hello!"

        return result
    }
}
