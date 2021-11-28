package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "book")
data class Book(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "price")
    var price: BigDecimal,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null,

    /*
        ManyToOne - Muitos livros pertencem a um customer
        customer_id pertence à chave secundaria na tabela book sql
    */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null

    )

