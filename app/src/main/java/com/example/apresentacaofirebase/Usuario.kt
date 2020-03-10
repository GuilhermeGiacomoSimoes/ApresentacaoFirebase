package com.example.apresentacaofirebase

import java.io.Serializable

class Usuario : Serializable {
    lateinit var uuid:String
    lateinit var nome:String
    lateinit var email:String
    lateinit var senha:String
}