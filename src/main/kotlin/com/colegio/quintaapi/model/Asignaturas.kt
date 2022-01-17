package com.colegio.quintaapi.model


import javax.persistence.*


@Entity
@Table(name = "asignaturas")

class Asignaturas {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var descricion: String? = null

    @Column(name = "estudiante_id")
    var estudianteId: Long? = null
}