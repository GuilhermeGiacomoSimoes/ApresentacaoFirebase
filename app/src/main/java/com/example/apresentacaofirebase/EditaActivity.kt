package com.example.apresentacaofirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelUuid
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_edita.*

class EditaActivity : AppCompatActivity() {

    private lateinit var firebase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edita)

        firebase = ConfiguracaoFirebase.firebase

        val usuario = intent.getSerializableExtra("usuario") as Usuario
        preenche(usuario)

        btnGravarAlteracao.setOnClickListener { gravar(usuario.uuid) }
        btnExcluir.setOnClickListener { excluir(usuario.uuid) }
    }

    private fun preenche(usuario:Usuario){
        edtEditaNome.setText(usuario.nome)
        edtEditaEmail.setText(usuario.email)
        edtEditaSenha.setText(usuario.senha)
    }

    private fun gravar(uuid: String){
        val usuario = Usuario()
        usuario.nome = edtEditaNome.text.toString()
        usuario.email = edtEditaEmail.text.toString()
        usuario.senha = edtEditaSenha.text.toString()
        usuario.uuid = uuid

        firebase.child("usuario").child(uuid).setValue(usuario)
    }

    private fun excluir(uuid: String){
        firebase.child("usuario").child(uuid).removeValue()
    }
}
