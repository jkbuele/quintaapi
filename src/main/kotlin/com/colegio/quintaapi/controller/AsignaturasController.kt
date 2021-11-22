package com.colegio.quintaapi.controller

import com.colegio.quintaapi.model.Asignaturas
import com.colegio.quintaapi.service.AsignaturasService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/asignaturas")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])


class AsignaturasController {


    @Autowired
    lateinit var asignaturasService: AsignaturasService

    @GetMapping
    fun list(): List<Asignaturas>{
        return asignaturasService.list()
    }

    @PostMapping
    fun save(@RequestBody asignaturas: Asignaturas): Asignaturas {
        return asignaturasService.save(asignaturas)

    }
    @PutMapping
    fun update(@RequestBody asignaturas: Asignaturas): Asignaturas {
        return  asignaturasService.update(asignaturas)

    }
    @PatchMapping
    fun updateDescricion(@RequestBody asignaturas: Asignaturas): Asignaturas {
        return  asignaturasService.updateDescricion(asignaturas)

    }
    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean{
        return asignaturasService.delete(id)
    }


}