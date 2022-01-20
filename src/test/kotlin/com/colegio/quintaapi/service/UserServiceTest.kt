package com.colegio.quintaapi.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @Test
    fun calclMultiplicationIfIsPair(){
        val response = userService.calcMultiplication(2,4)
        Assertions.assertEquals(8,response)
    }

    @Test
    fun calclMultiplicationIfIsNoPair(){
        val response = userService.calcMultiplication(1, 4)
        Assertions.assertEquals(4,response)
    }

    @Test
    fun restNineIfIsMenorTen(){
        //si el number es mayor o igual a 10 ,devolver restando 9
        //si no devolver elmismo numero
        val response = userService.restNine(8)
        Assertions.assertEquals(8,response)
    }

    @Test
    fun restNineIfIsMajorTen(){

        val response = userService.restNine( 10)
        Assertions.assertEquals(1,response)
    }

    @Test
    fun subtacFromNextTen(){

        val response = userService.subtacFromNextTen( 0)
        Assertions.assertEquals(10,response)
    }

    @Test
    fun ValidarCedula(){
        val response = userService.validarCedula("0150265759")
        Assertions.assertEquals(true,response)
    }

    @Test
    fun ValidarCedulaFalso(){
        val response = userService.validarCedula("0150265755")
        Assertions.assertEquals(false,response)
    }




}