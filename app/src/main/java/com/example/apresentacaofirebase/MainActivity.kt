package com.example.apresentacaofirebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var firebase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebase = ConfiguracaoFirebase.firebase

        btnGravar.setOnClickListener { gravar() }
        btnLista.setOnClickListener { startList() }
    }

    private fun startList(){
        startActivity(Intent(this, ListaUsuariosActivity ::class.java))
    }

    private fun gravar(){

        val usuario = Usuario()
        usuario.nome = edtNome.text.toString()
        usuario.email = edtEmail.text.toString()
        usuario.senha = edtSenha.text.toString()
        usuario.uuid = UUID.randomUUID().toString()

        try {
            //insert into usuario values nome, email, senha
            firebase.child("usuario").child(usuario.uuid).setValue(usuario)
            Toast.makeText(this, "usuario cadastrado", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(this, "ERRO :( $e", Toast.LENGTH_LONG).show()
        }
    }
}
