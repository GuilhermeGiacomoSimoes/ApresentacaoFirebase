package com.example.apresentacaofirebase

import com.google.firebase.database.FirebaseDatabase

class ConfiguracaoFirebase {
    companion object{
        val firebase = FirebaseDatabase.getInstance().reference
    }
}