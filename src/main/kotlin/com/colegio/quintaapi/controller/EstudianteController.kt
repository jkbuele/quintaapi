package com.colegio.quintaapi.controller

import com.colegio.quintaapi.model.Estudiante
import com.colegio.quintaapi.service.EstudianteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/estudiante")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])



class EstudianteController {

    @Autowired
    lateinit var estudianteService: EstudianteService

    @GetMapping
    fun list(): List<Estudiante>{
        return estudianteService.list()
    }
    @PostMapping
    fun save(@RequestBody estudiante: Estudiante): Estudiante {
        return estudianteService.save(estudiante)

    }
    @PutMapping
    fun update(@RequestBody estudiante: Estudiante): Estudiante {
        return  estudianteService.update(estudiante)

    }
    @PatchMapping
    fun updateNombre(@RequestBody estudiante: Estudiante): Estudiante {
        return  estudianteService.updateNombre(estudiante)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean{
        return estudianteService.delete(id)
    }


}