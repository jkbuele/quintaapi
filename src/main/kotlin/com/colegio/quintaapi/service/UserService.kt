package com.colegio.quintaapi.service

import com.colegio.quintaapi.model.Colegio
import com.colegio.quintaapi.model.User
import com.colegio.quintaapi.repository.ColegioRepository
import com.colegio.quintaapi.repository.EstudianteRepository
import com.colegio.quintaapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
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

    fun update(user: User): User{
        return  userRepository.save(user)

    }



}