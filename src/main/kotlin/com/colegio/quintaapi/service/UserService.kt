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
         var cedula = (cedula )
        return true

    }



}