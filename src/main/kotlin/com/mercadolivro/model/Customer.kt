package com.mercadolivro.model

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
data class Customer(
    var id: String,
    var name: String,
    var email: String
)
