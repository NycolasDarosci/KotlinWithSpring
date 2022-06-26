package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus.DELETADO
import com.mercadolivro.enums.BookStatus.CANCELADO
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors.ML102
import com.mercadolivro.exception.BadRequestException
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

        /*
            ManyToOne - Muitos livros pertencem a um customer
            customer_id pertence à chave secundaria na tabela book sql
        */
        @ManyToOne
        @JoinColumn(name = "customer_id")
        var customer: Customer? = null

) {
    /*
        field - valor atual do campo
        value - novo valor a ser usado
    */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set (value) {
            if (field == CANCELADO || field == DELETADO) {
                throw BadRequestException(
                        message = ML102.message.format(field),
                        errorCode = ML102.code
                )
            }
            field = value
        }

    constructor(
            id: Int? = null,
            name: String,
            price: BigDecimal,
            customer: Customer? = null,
            status: BookStatus?
    ) : this(id, name, price, customer) {
        this.status = status
    }

}

