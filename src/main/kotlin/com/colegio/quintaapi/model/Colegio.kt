package com.colegio.quintaapi.model

import javax.persistence.*

@Entity
@Table(name = "colegio")

class Colegio {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombreC: String? = null
    var direccion: String? = null
}