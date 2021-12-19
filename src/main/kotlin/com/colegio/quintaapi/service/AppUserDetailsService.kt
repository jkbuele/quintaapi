package com.colegio.quintaapi.service

import com.colegio.quintaapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

import java.util.ArrayList

@Service
class AppUserDetailsService: UserDetailsService  {

    @Autowired
    lateinit var userService: UserService

    override fun loadUserByUsername(username: String?): UserDetails {
       //llamar a usuarios de la base de datos
      val response=  userService.getUser(username)


        return User(response?.username,"{noop}"+response?.password, ArrayList())
    }
}