package com.colegio.quintaapi.model


import javax.persistence.*

@Entity
@Table(name = "estudiante")

class Estudiante {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombreE: String? = null
    var apellido: String? = null
    @Column(name = "colegio_id")
    var colegioId: Long? = null
}