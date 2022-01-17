package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Colegio
import com.colegio.quintaapi.model.User
import com.colegio.quintaapi.repository.ColegioRepository
import com.colegio.quintaapi.repository.EstudianteRepository
import com.colegio.quintaapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException


@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository


    fun list(): List<User> {

        return userRepository.findAll()
    }

    fun getUser(username: String? ): User?{
        try{
            val response = userRepository.findByUsername(username)
                ?:throw Exception("No existe usuario")
            return response

        }catch (ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun save (@RequestBody user: User):User{
        try {
            user.username?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("El campo username se encuentra vacio")

            user.password?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo password se encuentra vacio")

            user.cedula?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo cedula se encuentra vacio")

            return userRepository.save(user)
        }catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun update(@RequestBody user: User): User{
        try {

            val response = userRepository.findById(user.id)
                ?: throw Exception("El ID ${user.id} de usuarios no existe")

            user.username?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo username se encuentra vacio")

            user.password?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo password se encuentra vacio")

            user.cedula?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo cedula se encuentra vacio")

            return userRepository.save(user)

        } catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)

        }

    }

    fun updatePassword (user: User): User {
        try {
            user.password?.takeIf {it.trim().isNotEmpty()}
                ?: throw Exception("El campo password se encuentra vacio")


            val response = userRepository.findById(user.id)
                ?: throw Exception("El ID ${user.id} de usuarios no existe")
            response.apply {
                this.password=user.password
            }
            return userRepository.save(response)

        }catch(ex: Exception){
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND, ex.message, ex)
        }
    }

    fun delete (id:Long): Boolean{
        userRepository.deleteById(id)
        return true
    }

    //metodos d ela logica
    fun calcMultiplication(index: Int, number:Int):Int{

        if(index %2 ==0){
            return number * 2
        }
      else{
          return number
        }
    }


    fun restNine(number : Int): Int{
        if (number in 10..18){
            return number - 9
        }else {
            return number
        }
    }



    fun subtacFromNextTen(number : Int): Int {
        var decena = (number/10) + 1
        var response = (decena * 10) - number

        return response

    }

    fun validarCedula(cedula: String) : Boolean{

        val primerosNumeros = cedula.substring(0, 2)
        val numero1 = cedula.substring(0, 1)
        val numero2 = cedula.substring(1, 2)
        val numero3 = cedula.substring(2, 3)
        val numero4 = cedula.substring(3, 4)
        val numero5 = cedula.substring(4, 5)
        val numero6 = cedula.substring(5, 6)
        val numero7 = cedula.substring(6, 7)
        val numero8 = cedula.substring(7, 8)
        val numero9 = cedula.substring(8, 9)
        val numero10 = cedula.substring(9, 10)

        if (cedula.length != 10) {
            return false
        }

        if (primerosNumeros.toInt() < 1 || primerosNumeros.toInt() > 24) {
            return false
        }
        if (numero3.toInt() > 6) {
            return false
        }
        val par = (numero2.toInt() + numero4.toInt() + numero6.toInt() + numero8.toInt())

        val ImparNumero1 = numero1.toInt() * 2
        if (ImparNumero1 > 9) {
            ImparNumero1 - 9
        }
        var ImparNumero3 = numero3.toInt() * 2
        if (ImparNumero3 > 9) {
            ImparNumero3 -= 9
        }
        var ImparNumero5 = numero5.toInt() * 2
        if (ImparNumero5 > 9) {
            ImparNumero5 -= 9
        }
        var ImparNumero7 = numero7.toInt() * 2
        if (ImparNumero7 > 9) {
            ImparNumero7 -= 9
        }
        var ImparNumero9 = numero9.toInt() * 2
        if (ImparNumero9 > 9) {
            ImparNumero9 -= 9
        }

        val impares = ImparNumero1 + ImparNumero3 + ImparNumero5 +ImparNumero7 + ImparNumero9
        val sumTotal = (par + impares)

        val operation = (sumTotal/10) + 1
        var valida = (operation*10) - sumTotal
        if (valida == 10) {
            valida = 0
        }
        if (valida!= numero10.toInt()){
            return false
        }
        return true
    }



}