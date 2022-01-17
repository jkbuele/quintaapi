package com.colegio.quintaapi

import com.colegio.quintaapi.service.ColegioService
import com.colegio.quintaapi.service.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class QuintaapiApplicationTests {

	@Autowired
	lateinit var userService: UserService


	@Test
	fun contextLoads() {

	}



}
