package com.colegio.quintaapi.controller

import com.colegio.quintaapi.model.Colegio
import com.colegio.quintaapi.service.ColegioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/colegio")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT])


class ColegioController {

    @Autowired
    lateinit var colegioService: ColegioService

    @GetMapping
    fun list(): List<Colegio>{
        return colegioService.list()
    }

    @PostMapping
    fun save(@RequestBody colegio: Colegio):Colegio {
        return colegioService.save(colegio)
    }

    @PutMapping
    fun update(@RequestBody colegio: Colegio): Colegio {
        return  colegioService.update(colegio)

    }

    @PatchMapping
    fun updateDireccion(@RequestBody colegio: Colegio): Colegio{
        return  colegioService.updateDireccion(colegio)
    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean {
        return colegioService.delete(id)
    }



}