package com.colegio.quintaapi.controller

import com.colegio.quintaapi.model.User
import com.colegio.quintaapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])


class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    fun list(): List<User>{
        return userService.list()

    }
    @PostMapping
    fun save (@RequestBody user: User): User {
        return userService.save(user)
    }

    @PutMapping
    fun update(@RequestBody user: User): User {
        return userService.update(user)

    }

    @PatchMapping
    fun updatePassword (@RequestBody user: User): User{
        return userService.updatePassword(user)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean{
        return userService.delete(id)
    }
}