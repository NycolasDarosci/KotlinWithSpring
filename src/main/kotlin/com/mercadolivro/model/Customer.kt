package com.mercadolivro.model

import com.mercadolivro.enums.CustomerStatus
import javax.persistence.*

/*
    não é incomum criar classe com objetivo de reter dados(hold data).
    Algumas funcionalidades padrões e algumas funções de utilidade são,
    frequentemente derivado dos dados. No Kotlin, são chamados data classes

    o compilador automaticamente libera das propriedades declaradas no construtor
    primario:
        - equals() / hashcode() pair;
        - toString() of the Form "Customer(id=1, name=Jose, email=example@ex.com)";
        - funções ComponentN() correspondentes às propriedades em sua ordem de declaração;
        - função copy()
*/
@Entity
@Table(name = "customer")
data class Customer(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name =  "name")
    var name: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus,

    @Column
    var password: String
)
